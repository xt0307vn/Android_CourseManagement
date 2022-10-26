package com.example.android_coursemanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    Random random = new Random();
    CoursesFragment coursesFragment;
    List<Courses> courses;
    Firebase firebase = new Firebase();
    public void setData(List<Courses> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }


    public CourseAdapter(CoursesFragment coursesFragment, List<Courses> courses) {
        this.coursesFragment = coursesFragment;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Courses course = courses.get(position);
        if(courses == null){
            return;
        }
        int a = setColorIndex(position, 6);
        holder.item_background.setBackgroundColor(android.graphics.Color.argb(255, setColor(a).getR(), setColor(a).getG(), setColor(a).getB()));
        holder.item_courseID.setText(course.getCourse_id());
        holder.item_courseName.setText(course.getCourse_name());
        holder.item_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course_id = holder.item_courseID.getText().toString().trim();
                showDialogDelete("Warning", "Are you sure?", course_id);
            }
        });

        holder.item_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(coursesFragment.getContext(), EditCourseActivity.class);
                String course_id = holder.item_courseID.getText().toString().trim();
                intent.putExtra("course_id", course_id);
                coursesFragment.getContext().startActivity(intent);


            }
        });

        holder.item_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(coursesFragment.getContext(), CourseDetailActivity.class);
                String course_id = holder.item_courseID.getText().toString().trim();
                intent.putExtra("course_id", course_id);
                coursesFragment.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(courses != null) {
            return courses.size();
        }
        return 0;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView  item_courseName, item_courseID;
        Button item_Delete, item_Edit, item_Detail;
        LinearLayout item_background;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            item_courseName = itemView.findViewById(R.id.item_courseName);
            item_courseID = itemView.findViewById(R.id.item_courseID);
            item_Delete = itemView.findViewById(R.id.item_Delete);
            item_Edit = itemView.findViewById(R.id.item_Edit);
            item_Detail = itemView.findViewById(R.id.item_Detail);
            item_background = itemView.findViewById(R.id.item_background);
        }
    }


    public static class Color {
        int r, g, b;

        public Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    public Color setColor(int index) {
        List<Color> colorList = new ArrayList<>();
        colorList.add(new Color(204, 255, 204));
        colorList.add(new Color(243, 236, 234));
        colorList.add(new Color(224, 235, 235));
        colorList.add(new Color(217, 238, 225));
        colorList.add(new Color(255, 255, 204));
        colorList.add(new Color(204, 242, 255));
        colorList.add(new Color(204, 204, 153));

        return colorList.get(index);
    }

    public int setColorIndex(int position, int index) {
        int a = (int) Math.floor(position/index);

        return position - a*index;
    }

    public void showDialogDelete(String warning, String message, String course_id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(coursesFragment.getContext());
        alertDialog.setMessage(message);
        alertDialog.setIcon(R.drawable.ic_warning);
        alertDialog.setTitle(warning);
        alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebase.collection_courses.document(course_id).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(coursesFragment.getContext(), "Delete successfully", Toast.LENGTH_SHORT).show();
                                Intent ref = new Intent(coursesFragment.getContext(), MainActivity.class);
                                coursesFragment.showData();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(coursesFragment.getContext(), "Delete failling", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        alertDialog.show();
    }



}
