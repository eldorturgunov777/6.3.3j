package com.example.a633j.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eldor Turgunov on 25.06.2022.
 * 6.3.3j
 * eldorturgunov777@gmail.com
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Note.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        onCreate(db);
    }

    public long insertNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note);
        long id = db.insert(Note.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Note getNote(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        @SuppressLint("Range") Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));

        cursor.close();
        return note;
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        db.close();

        return notes;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }
}
