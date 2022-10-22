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

public class SignupActivity extends AppCompatActivity {
    EditText signup_username, signup_password, signup_lastname, signup_firstname, signup_phone, signup_email;
    TextView signup_linksignin;
    Button signup_btn;
    String user_name, user_password, user_lastname, user_firstname, user_phone, user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        connectIDLayout();

        signup_linksignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }

    public void connectIDLayout() {
        signup_username = findViewById(R.id.signup_edtUsername);
        signup_password = findViewById(R.id.signup_edtPassword);
        signup_lastname = findViewById(R.id.signup_edtLasttname);
        signup_firstname = findViewById(R.id.signup_edtFirstname);
        signup_phone = findViewById(R.id.signup_edtPhone);
        signup_email = findViewById(R.id.signup_edtEmail);
        signup_linksignin = findViewById(R.id.link_signin);
        signup_btn = findViewById(R.id.signup_btnSignup);
    }

    public void gotoSignin() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }

    public void getDataInput() {
        user_name = signup_username.getText().toString().trim();
        user_password = signup_password.getText().toString().trim();
        user_firstname = signup_lastname.getText().toString().trim();
        user_lastname = signup_firstname.getText().toString().trim();
        user_phone = signup_phone.getText().toString().trim();
        user_email = signup_email.getText().toString().trim();
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

    public boolean checkInput() {
        if(user_name.isEmpty() || user_password.isEmpty() || user_firstname.isEmpty() || user_lastname.isEmpty() || user_phone.isEmpty() || user_email.isEmpty()) {
            return true;
        }
        return false;
    }
}