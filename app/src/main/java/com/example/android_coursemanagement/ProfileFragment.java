package com.example.android_coursemanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
    MainActivity mainActivity = new MainActivity();
    TextView profile_userName, profile_User_id, profile_User_fullname, profile_User_phone, profile_User_email;
    Button progile_signOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectID(view);


    }

    public void connectID(View view) {
        profile_userName = view.findViewById(R.id.profile_userName);
        profile_User_id = view.findViewById(R.id.profile_User_id);
        profile_User_fullname = view.findViewById(R.id.profile_User_fullname);
        profile_User_phone = view.findViewById(R.id.profile_User_phone);
        profile_User_email = view.findViewById(R.id.profile_User_email);
        progile_signOut = view.findViewById(R.id.progile_signOut);
    }

}