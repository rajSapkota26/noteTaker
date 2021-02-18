package com.technoabinash.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewNote extends AppCompatActivity {
TextView textDateView,textNoteView,textTitleView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        init();
        show();
    }

    public void editNote(View view) {
        Intent intent = new Intent(this, NoteCreated.class);
        intent.putExtra("type",26);
        intent.putExtra("id",id);
        intent.putExtra("title",textTitleView.getText().toString());
        intent.putExtra("note", textNoteView.getText().toString());
        startActivity(intent);

    }

    private void init() {
        textTitleView=findViewById(R.id.textTitleView);
        textNoteView=findViewById(R.id.textNoteView);
        textDateView=findViewById(R.id.textDateView);
    }

    public void backNote(View view) {
        returnToMain();
    }

    private void returnToMain() {
        Intent intent = new Intent(ViewNote.this, MainActivity.class);
        startActivity(intent);
    }
    public  void  show(){
        Intent intent1=getIntent();
        id=intent1.getIntExtra("id",0);
        textDateView.setText("Created On: "+  intent1.getStringExtra("date"));
        textNoteView.setText(intent1.getStringExtra("note"));
        textTitleView.setText(intent1.getStringExtra("title"));
    }
}