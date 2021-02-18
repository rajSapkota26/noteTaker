package com.technoabinash.notetaker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technoabinash.notetaker.model.MyNote;

import java.util.ArrayList;
import java.util.List;


public class NoteDao extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myNote.db";


    public NoteDao(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Note (id integer primary key autoincrement,title text,description text,noteDate text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP table if exists Note";
        db.execSQL(sql);
        onCreate(db);
    }
    public boolean addNote(String title, String description, String noteDate) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("noteDate", noteDate);

        long id = database.insert("Note", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<MyNote> getAllNote() {
        ArrayList<MyNote> list = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        String query = "select id,title,description,noteDate from Note ";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                MyNote orderModel = new MyNote();
                orderModel.setId(cursor.getInt(0));
                orderModel.setTitle(cursor.getString(1));
                orderModel.setNote(cursor.getString(2));
                orderModel.setNoteDate(cursor.getString(3));
                list.add(orderModel);
            }
        }
        cursor.close();
        database.close();
        return list;
    }

    public boolean updateNote(int eId, String cName, String cPhone, String cEmail) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues data = new ContentValues();
        data.put("title", cName);
        data.put("description", cPhone);
        data.put("noteDate", cEmail);

        int f = database.update("Note", data, "id=" + eId, null);
        if (f > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor getNoteById(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "select * from Note where id=" + id;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public int deleteNote(int id) {
        SQLiteDatabase database = getWritableDatabase();
        int dId = database.delete("Note", "id=" + id, null);

        return dId;
    }
}
