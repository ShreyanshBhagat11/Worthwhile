package com.example.worthwhile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateNote extends AppCompatActivity {

    private EditText mcreatetitleofnote,mcreatecontentofnote;
    private FloatingActionButton msavenote;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar mprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);


        mcreatetitleofnote = findViewById(R.id.createtitleofnote);
        mcreatecontentofnote = findViewById(R.id.createcontentofnote);
        msavenote = findViewById(R.id.savenote);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mprogressBar = findViewById(R.id.progress_bar_of_create_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create new note");
        msavenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mcreatetitleofnote.getText().toString();
                String content = mcreatecontentofnote.getText().toString();

                if(title.isEmpty() || content.isEmpty())
                {
                    Toast.makeText(CreateNote.this, "Both field are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    // store note into firestore
                    mprogressBar.setVisibility(View.VISIBLE);

                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document();
                    Map<String,Object> notes = new HashMap<>();
                    notes.put("title",title);
                    notes.put("content",content);

                    documentReference.set(notes).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finish();
                            startActivity(new Intent(CreateNote.this,NotesActivity.class));
                            mprogressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(CreateNote.this, "Note created successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateNote.this, "Failed to create note", Toast.LENGTH_SHORT).show();
                            mprogressBar.setVisibility(View.INVISIBLE);
                        }
                    });


                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}