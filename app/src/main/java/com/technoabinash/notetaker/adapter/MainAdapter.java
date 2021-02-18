package com.technoabinash.notetaker.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technoabinash.notetaker.R;
import com.technoabinash.notetaker.ViewNote;
import com.technoabinash.notetaker.database.NoteDao;
import com.technoabinash.notetaker.model.MyNote;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder>{
    Context context;
    ArrayList<MyNote> myNotes;

    public MainAdapter(Context context,  ArrayList<MyNote> myNotes) {
        this.context = context;
        this.myNotes = myNotes;
    }

    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.viewHolder holder, int position) {
        final  MyNote myNote = myNotes.get(position);
        holder.textDate.setText(myNote.getNoteDate());
        holder.textTitle.setText(myNote.getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewNote.class);
            intent.putExtra("id",(myNote.getId()));
            intent.putExtra("title", myNote.getTitle());
            intent.putExtra("note", myNote.getNote());
            intent.putExtra("date", myNote.getNoteDate());
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setTitle("Delete Note").setMessage("You want to delete this Note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NoteDao helper = new NoteDao(context);
                                if (helper.deleteNote((myNote.getId()))>-1) {
                                    Toast.makeText(context, "Note Deleted successfully...", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Note Deleted failed...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textDate,textTitle;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textDate=itemView.findViewById(R.id.textDate);
            textTitle=itemView.findViewById(R.id.textTitle);
        }
    }
}
