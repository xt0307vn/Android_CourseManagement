package com.example.android_coursemanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class ProfileFragment extends Fragment {
    TextView profile_userName, profile_User_id, profile_User_fullname, profile_User_phone, profile_User_email;
    Button progile_signOut;
    SharedPreferences sharedPreferences;
    Firebase firebase;
    String user_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectID(view);
        firebase = new Firebase();
        sharedPreferences = getContext().getSharedPreferences("user_name", Context.MODE_PRIVATE);
        user_name = sharedPreferences.getString("user_name", "Null");
        Toast.makeText(getContext(), "" +user_name, Toast.LENGTH_SHORT).show();

        setT("â");

//        firebase.collection_users.whereEqualTo("user_name", user_name)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()) {
//                            QuerySnapshot doc = task.getResult();
////                            Toast.makeText(getContext(), doc.getDocuments().get(0).getString("user_name"), Toast.LENGTH_SHORT).show();
////                            Toast.makeText(getContext(), doc.getDocuments().get(0).getString("user_email"), Toast.LENGTH_SHORT).show();
////                            if(doc.size() == 0) {
////                                profile_userName.setText("Không");
////                            } else {
////                                profile_userName.setText("Có");
////                            }
//
//                        }
//                    }
//                });

//        showData(user_name);


    }

    public void connectID(View view) {
        profile_userName = view.findViewById(R.id.profile_userName);
        profile_User_id = view.findViewById(R.id.profile_User_id);
        profile_User_fullname = view.findViewById(R.id.profile_User_fullname);
        profile_User_phone = view.findViewById(R.id.profile_User_phone);
        profile_User_email = view.findViewById(R.id.profile_User_email);
        progile_signOut = view.findViewById(R.id.progile_signOut);
    }

    public void setT(String name) {
        profile_userName.setText("1");
    }

    public void showData(String user_name) {
        firebase.collection_users.whereEqualTo("user_name", user_name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            QuerySnapshot doc = task.getResult();
//                            Toast.makeText(getContext(), doc.getDocuments().get(0).getString("user_name"), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getContext(), doc.getDocuments().get(0).getString("user_email"), Toast.LENGTH_SHORT).show();
                            profile_userName.setText(doc.getDocuments().get(0).getString("user_email"));
//                            if(doc.size() == 0) {
//                                profile_userName.setText("Không");
//                            } else {
//                                profile_userName.setText("Có");
//                            }

                        }
                    }
                });
    }

}