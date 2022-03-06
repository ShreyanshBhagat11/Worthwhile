package com.example.worthwhile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {

    private EditText mforgetPasswordField;
    private TextView mgoBackToLogin;
    private Button mpasswordRecoverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mforgetPasswordField = findViewById(R.id.recover_using_email);
        mgoBackToLogin = findViewById(R.id.go_back_to_login);
        mpasswordRecoverBtn = findViewById(R.id.password_recover_button);


        mgoBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mpasswordRecoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mforgetPasswordField.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Toast.makeText(ForgetPassword.this, "Enter your email first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // send email for resetting password
                }
            }
        });
    }
}