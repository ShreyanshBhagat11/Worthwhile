package com.example.worthwhile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Notedetails extends AppCompatActivity {

    private TextView mtitleofnotedetails,mcontentofnotedetails;
    FloatingActionButton mgotoeditnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mtitleofnotedetails = findViewById(R.id.titleofnotedetails);
        mcontentofnotedetails = findViewById(R.id.contentofnotedetails);
        mgotoeditnote = findViewById(R.id.goto_edit_note);

        Intent data = getIntent();

        mgotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Editnoteactivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));
                finish();
                startActivity(intent);
            }
        });
        mcontentofnotedetails.setText(data.getStringExtra("content"));
        mtitleofnotedetails.setText(data.getStringExtra("title"));
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