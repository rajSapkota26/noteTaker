package com.technoabinash.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.technoabinash.notetaker.database.NoteDao;
import com.technoabinash.notetaker.model.MyNote;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoteCreated extends AppCompatActivity {
    Button save, cancel;
    EditText editTextDescription, editTextTitle;

    NoteDao myNote;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_created);
        init();
        show();

    }


    public void saveNote(View view) {
        int type = getIntent().getIntExtra("type", 0);
        if (type == 26) {
           boolean t= myNote.updateNote(getIntent().getIntExtra("id", 0),editTextTitle.getText().toString(), editTextDescription.getText().toString(), date);
            if (t) {
                Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                backToMain();
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }else{
            boolean f = myNote.addNote(editTextTitle.getText().toString(), editTextDescription.getText().toString(), date);
            if (f) {
                Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
                backToMain();
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init() {
        save = findViewById(R.id.saveBtn);
        cancel = findViewById(R.id.cancelBtn);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextTitle = findViewById(R.id.editTextTitle);
        myNote = new NoteDao(this);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        date = dateFormat.format(calendar.getTime());
    }

    public void cancelNote(View view) {
          new AlertDialog.Builder(view.getContext()).setTitle("You Want to cancel this Note").setMessage("are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backToMain();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    private void show() {
        editTextTitle.setText(getIntent().getStringExtra("title"));
        editTextDescription.setText(getIntent().getStringExtra("note"));
    }
    private void backToMain() {
        Intent intent = new Intent(NoteCreated.this, MainActivity.class);
        startActivity(intent);
    }


}