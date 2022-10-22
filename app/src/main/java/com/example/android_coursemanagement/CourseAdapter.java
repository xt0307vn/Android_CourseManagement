package com.example.android_coursemanagement;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Courses> courses;
    Firebase firebase = new Firebase();
    public void setData(List<Courses> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public CourseAdapter (Context context) {
        this.context = context;
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
        holder.item_courseID.setText(course.getCourse_id());
        holder.item_courseName.setText(course.getCourse_name());
        holder.item_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course_id = holder.item_courseID.getText().toString().trim();
                firebase.collection_courses.document(course_id).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                                Intent ref = new Intent(context, MainActivity.class);
                                context.startActivity(ref);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Delete failing", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        holder.item_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditCourseActivity.class);
                String course_id = holder.item_courseID.getText().toString().trim();
                intent.putExtra("course_id", course_id);
                context.startActivity(intent);


            }
        });

        holder.item_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CourseDetailActivity.class);
                String course_id = holder.item_courseID.getText().toString().trim();
                intent.putExtra("course_id", course_id);
                context.startActivity(intent);
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
        RelativeLayout item_Course;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            item_courseName = itemView.findViewById(R.id.item_courseName);
            item_courseID = itemView.findViewById(R.id.item_courseID);
            item_Delete = itemView.findViewById(R.id.item_Delete);
            item_Edit = itemView.findViewById(R.id.item_Edit);
            item_Detail = itemView.findViewById(R.id.item_Detail);
        }
    }

}
