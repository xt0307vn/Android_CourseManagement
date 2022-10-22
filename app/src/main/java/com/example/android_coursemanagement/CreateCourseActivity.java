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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class CreateCourseActivity extends AppCompatActivity {
    EditText createcourse_Edtname, createcourse_Edtwhat, createcourse_Edtwhy, createcourse_Edtcreator;
    ImageView createcourse_btnBack;
    Button createcourse_btnCreate;
    String str_courseName, str_courseWhat, str_courseWhy, str_courseCreator;
    Firebase firebase = new Firebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        connectID();

        createcourse_btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                Map<String, Object> course_create = new HashMap<>();
                course_create.put("course_name", str_courseName);
                course_create.put("course_what", str_courseWhat);
                course_create.put("course_why", str_courseWhy);
                course_create.put("course_creator", str_courseCreator);
                firebase.collection_courses.add(course_create)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(CreateCourseActivity.this, "Create successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateCourseActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateCourseActivity.this, "Create failing", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });






    }
    public void connectID() {
        createcourse_Edtname = findViewById(R.id.createcourse_Edtname);
        createcourse_Edtwhat = findViewById(R.id.createcourse_Edtwhat);
        createcourse_Edtwhy = findViewById(R.id.createcourse_Edtwhy);
        createcourse_Edtcreator = findViewById(R.id.createcourse_Edtcreator);

        createcourse_btnBack = findViewById(R.id.createcourse_btnBack);

        createcourse_btnCreate = findViewById(R.id.createcourse_btnCreate);
    }

    public void getInput(){
        str_courseName = createcourse_Edtname.getText().toString().trim();
        str_courseWhat = createcourse_Edtwhat.getText().toString().trim();
        str_courseWhy = createcourse_Edtwhy.getText().toString().trim();
        str_courseCreator = createcourse_Edtcreator.getText().toString().trim();
    }
}