package com.example.worthwhile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mLogInEmail,mLogInPassword;
    private TextView mforgetPasswordText;
    private RelativeLayout mLogInBtn,mgoto_sign_up;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,NotesActivity.class));
        }
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
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                checkVerifiedEmail();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Account does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void checkVerifiedEmail()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified())
        {
            finish();
            startActivity(new Intent(MainActivity.this,NotesActivity.class));
            Toast.makeText(MainActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Verify your email first!", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}