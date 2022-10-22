package com.example.android_coursemanagement;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firebase {
    FirebaseFirestore db;
    CollectionReference collection_courses ;
    CollectionReference collection_users ;
    public Firebase() {
        db = FirebaseFirestore.getInstance();
        collection_courses = db.collection("Courses");
        collection_users = db.collection("Users");
    }

    // FIREBASE USERS

    // Create data collection Users
    public void createUser(String user_name, String user_password, String user_firstname, String user_lastname, String user_email, String user_phone) {
        Map<String, Object> user = new HashMap<>();
        user.put("user_name", user_name);
        user.put("user_password", user_password);
        user.put("user_firstname", user_firstname);
        user.put("user_lastname", user_lastname);
        user.put("user_email", user_email);
        user.put("user_phone", user_phone);

        collection_users.add(user);

    }




    // FIREBASE COURSES

    // Create data collection Courses
    public void createCourse(String course_name, String course_what, String course_why, String course_creator) {
        Map<String, Object> course = new HashMap<>();
        course.put("user_name", course_name);
        course.put("user_password", course_what);
        course.put("user_firstname", course_why);
        course.put("user_lastname", course_creator);

        collection_courses.add(course);
    }

    // Read data collection Courses

    public List<Courses> readCourses() {
        List<Courses> courses = new ArrayList<>();
        collection_courses.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        courses.add(new Courses(document.getId()
                                , document.getString("course_name")
                                ,document.getString("course_what")
                                , document.getString("course_why")
                                , document.getString("course_creator")));
                    }

                }
            }
        });

        return courses;

    }




}
