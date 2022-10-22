package com.example.android_coursemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {
    EditText signin_username, signin_password;
    TextView signin_linksignup;
    Button signin_btn;
    String user_name, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        connectIDLayout();
        signin_linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignup();
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gototMain("ádsadf");
            }
        });



    }

    public void connectIDLayout() {
        signin_username = findViewById(R.id.signin_edtUsername);
        signin_password = findViewById(R.id.signin_edtPassword);
        signin_linksignup = findViewById(R.id.link_signup);
        signin_btn = findViewById(R.id.signin_btnSignin);
    }

    public void gotoSignup() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void gotoMain(int id) {
        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBundle("data", bundle);
        startActivity(intent);
    }

    public void getInput() {
        user_name = signin_username.getText().toString().trim();
        user_password = signin_password.getText().toString().trim();
    }

    public boolean checkInput() {
        if(user_name.isEmpty() || user_password.isEmpty()) {
            return false;
        }
        return true;
    }

    public void showDialogOk(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    public void gototMain(String user_name) {
        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
        startActivity(intent);
    }
}