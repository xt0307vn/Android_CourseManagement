package com.example.android_coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {
    EditText editcourse_Edtname, editcourse_Edtwhat, editcourse_Edtwhy, editcourse_Edtcreator;
    ImageView editcourse_btnBack;
    Button editcourse_btnUpdate;
    TextView editcourse_Textwhat, editcourse_Textwhy, editcourse_Textcreator;
    Firebase firebase = new Firebase();
    String course_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        connectID();
        editcourse_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null ) {
            course_id = bundle.getString("course_id");
            DocumentReference doc = firebase.collection_courses.document(course_id);
            doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            editcourse_Edtname.setText(document.getString("course_name"));
                            editcourse_Edtwhat.setText(document.getString("course_what"));
                            editcourse_Edtwhy.setText(document.getString("course_why"));
                            editcourse_Edtcreator.setText(document.getString("course_creator"));
                        } else {
                            Toast.makeText(EditCourseActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        editcourse_btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference doc = firebase.collection_courses.document(course_id);
                Map<String, Object> course_update = new HashMap<>();
                course_update.put("course_name", editcourse_Edtname.getText().toString().trim());
                course_update.put("course_what", editcourse_Edtwhat.getText().toString().trim());
                course_update.put("course_why", editcourse_Edtwhy.getText().toString().trim());
                course_update.put("course_creator", editcourse_Edtcreator.getText().toString().trim());
                doc.update(course_update)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditCourseActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditCourseActivity.this, "Update failing", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });




    }

    public void connectID() {
        editcourse_Edtname = findViewById(R.id.editcourse_Edtname);
        editcourse_Edtwhat = findViewById(R.id.editcourse_Edtwhat);
        editcourse_Edtwhy = findViewById(R.id.editcourse_Edtwhy);
        editcourse_Edtcreator = findViewById(R.id.editcourse_Edtcreator);

        editcourse_btnBack = findViewById(R.id.editcourse_btnBack);
        editcourse_btnUpdate = findViewById(R.id.editcourse_btnUpdate);

        editcourse_Textwhat = findViewById(R.id.editcourse_Textwhat);
        editcourse_Textwhy = findViewById(R.id.editcourse_Textwhy);
        editcourse_Textcreator = findViewById(R.id.editcourse_Textcreator);
    }

    public void gotoMain() {
        Intent intent = new Intent(EditCourseActivity.this, MainActivity.class);
        startActivity(intent);
    }
}