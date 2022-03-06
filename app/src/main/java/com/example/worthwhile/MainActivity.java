package com.example.worthwhile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mLogInEmail,mLogInPassword;
    private TextView mforgetPasswordText;
    private RelativeLayout mLogInBtn,mgoto_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mLogInEmail = findViewById(R.id.login_email);
        mLogInPassword = findViewById(R.id.login_Password);
        mforgetPasswordText = findViewById(R.id.Forget_password_text);
        mLogInBtn = findViewById(R.id.login);
        mgoto_sign_up = findViewById(R.id.goto_sign_up);

        mLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mLogInEmail.getText().toString().trim();
                String password = mLogInPassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Invalid email address or password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Login the user
                }
            }
        });

        mforgetPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ForgetPassword.class);
                startActivity(intent);
            }
        });

        mgoto_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });




    }
}