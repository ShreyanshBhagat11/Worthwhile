package com.example.worthwhile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetPassword extends AppCompatActivity {

    private EditText mforgetPasswordField;
    private TextView mgoBackToLogin;
    private Button mpasswordRecoverBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mforgetPasswordField = findViewById(R.id.recover_using_email);
        mgoBackToLogin = findViewById(R.id.go_back_to_login);
        mpasswordRecoverBtn = findViewById(R.id.password_recover_button);

        firebaseAuth = FirebaseAuth.getInstance();

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
                String email = mforgetPasswordField.getText().toString().trim();
                if(email.isEmpty())
                {
                    Toast.makeText(ForgetPassword.this, "Enter your email first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // send email for resetting password
                    if (checkVerifiedEmail())
                    {
                        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    finish();
                                    startActivity(new Intent(ForgetPassword.this,MainActivity.class));
                                    Toast.makeText(ForgetPassword.this, "Email sent,You can recover your password using email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(ForgetPassword.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean checkVerifiedEmail()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified())
        {
            return true;
        }
        else
        {
            firebaseAuth.signOut();
            return false;
        }
    }
}