package com.example.android_coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CourseDetailActivity extends AppCompatActivity {
    TextView detailcourse_Textwhat, detailcourse_Textwhy, detailcourse_Textname, detailcourse_Textcreator;
    String course_name, course_title;
    ImageView detailcourse_btnBack;
    Firebase firebase = new Firebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        connectID();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ) {
            String course_id = bundle.getString("course_id");
            DocumentReference doc = firebase.collection_courses.document(course_id);
            doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            detailcourse_Textname.setText(document.getString("course_name"));
                            detailcourse_Textwhat.setText(document.getString("course_what"));
                            detailcourse_Textwhy.setText(document.getString("course_why"));
                            detailcourse_Textcreator.setText(document.getString("course_creator"));
                        } else {
                            Toast.makeText(CourseDetailActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        detailcourse_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetailActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public void connectID() {
        detailcourse_Textname = findViewById(R.id.detailcourse_Textname);
        detailcourse_Textwhat = findViewById(R.id.detailcourse_Textwhat);
        detailcourse_Textwhy = findViewById(R.id.detailcourse_Textwhy);
        detailcourse_Textcreator = findViewById(R.id.detailcourse_Textcreator);
        detailcourse_btnBack = findViewById(R.id.detailcourse_btnBack);
    }


}