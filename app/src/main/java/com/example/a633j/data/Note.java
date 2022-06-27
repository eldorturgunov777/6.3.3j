package com.example.a633j.data;

/**
 * Created by Eldor Turgunov on 27.06.2022.
 * 6.3.3j
 * eldorturgunov777@gmail.com
 */
public class Note {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";

    private int id;
    private String note;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT"
                    + ")";

    public Note() {
    }

    public Note(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }

}
