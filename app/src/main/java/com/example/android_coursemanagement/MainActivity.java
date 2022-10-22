package com.example.android_coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ProfileFragment profileFragment;
    CoursesFragment coursesFragment;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottomnavigationBar);
        profileFragment = new ProfileFragment();
        coursesFragment = new CoursesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, coursesFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_courses:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, coursesFragment).commit();
                        return true;
                    case R.id.item_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, profileFragment).commit();
                        return true;
                }
                return false;
            }
        });






    }
}