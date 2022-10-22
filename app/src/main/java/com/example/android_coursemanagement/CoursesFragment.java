package com.example.android_coursemanagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoursesFragment extends Fragment {
    ImageView image_createCourse;
    RecyclerView  fragment_courseRecycleView;
    CourseAdapter courseAdapter;
    Firebase firebase = new Firebase();
    CollectionReference cou = FirebaseFirestore.getInstance().collection("Courses");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment_courseRecycleView = view.findViewById(R.id. fragment_courseRecycleView);
        image_createCourse = view.findViewById(R.id.image_createCourse);
        image_createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateCourseActivity.class);
                startActivity(intent);
            }
        });


        courseAdapter = new CourseAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        fragment_courseRecycleView.setLayoutManager(gridLayoutManager);
        courseAdapter.setData(getlist());
        fragment_courseRecycleView.setAdapter(courseAdapter);
    }

    private List<Courses> getlist() {
        List<Courses> list = new ArrayList<>();
        firebase.collection_courses.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        list.add(new Courses(document.getId()
                                , document.getString("course_name")
                                ,document.getString("course_what")
                                , document.getString("course_why")
                                , document.getString("course_creator")));
                    }
                    courseAdapter.notifyDataSetChanged();
                }
            }
        });

        return list;
    }
}