package com.technoabinash.notetaker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class MyNote {

    private int id;
    private String title;
    private String note;
    private String noteDate;

    public MyNote(String title, String note, String noteDate) {
        this.title = title;
        this.note = note;
        this.noteDate = noteDate;
    }

    public MyNote() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
