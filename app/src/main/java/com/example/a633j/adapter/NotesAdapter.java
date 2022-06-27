package com.example.a633j.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a633j.R;
import com.example.a633j.data.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Eldor Turgunov on 27.06.2022.
 * 6.3.3j
 * eldorturgunov777@gmail.com
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private final Context context;
    private final List<Note> notesList;

    public NotesAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_page, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.note.setText(note.getNote());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;

        public MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.title);
        }
    }

}
