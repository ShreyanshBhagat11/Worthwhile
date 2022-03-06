package com.example.worthwhile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    private EditText mSignUpEmail,mSignUpPassword;
    private TextView mgotoLogin;
    private RelativeLayout mSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        mSignUpEmail = findViewById(R.id.signup_email);
        mSignUpPassword = findViewById(R.id.signup_password);
        mgotoLogin = findViewById(R.id.goto_login);
        mSignUpBtn = findViewById(R.id.signUp);


        mgotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mSignUpEmail.getText().toString().trim();
                String password = mSignUpPassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 7)
                {
                    Toast.makeText(signup.this, "Password length should be greater than 7", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Register user to firebase
                }
            }
        });


    }

}