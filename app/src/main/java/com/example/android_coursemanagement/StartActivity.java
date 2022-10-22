package com.example.android_coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {
    Button start_getStarted;
    private Object put;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebase = new Firebase();
        setContentView(R.layout.activity_start);
        connectID();

        start_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignin();

            }
        });

    }
    public void connectID() {
        start_getStarted = findViewById(R.id.start_getStarted);
    }
    public void gotoSignin() {
        Intent intent = new Intent(StartActivity.this, SigninActivity.class);
        startActivity(intent);
    }
}