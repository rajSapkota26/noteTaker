package com.technoabinash.notetaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technoabinash.notetaker.adapter.MainAdapter;
import com.technoabinash.notetaker.database.NoteDao;
import com.technoabinash.notetaker.model.MyNote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton btn;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private  NoteDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ArrayList<MyNote> myNotes=new ArrayList<>();
        myNotes=dao.getAllNote();
        MainAdapter mainAdapterAdapter = new MainAdapter( this,myNotes);

        recyclerView.setAdapter(mainAdapterAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager1);
    }
    public void addNote(View view) {
        Intent intent=new Intent(MainActivity.this,NoteCreated.class);
        startActivity(intent);
    }
    private void init() {
        recyclerView=findViewById(R.id.noteListView);
         btn=findViewById(R.id.addNoteButton);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        date = dateFormat.format(calendar.getTime());
        dao=new NoteDao(this);
    }


}