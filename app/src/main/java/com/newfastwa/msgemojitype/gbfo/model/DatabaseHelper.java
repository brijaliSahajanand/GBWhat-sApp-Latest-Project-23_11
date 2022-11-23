package com.newfastwa.msgemojitype.gbfo.model;

public class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static String COLUMN_CONTENT = "content";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_THICKNESS = "thickness";
    public static String COLUMN_TITLE = "title";
    private static final String DATABASE_NAME = "note.db";
    private static final int DATABASE_VERSION = 1;
    public static String NOTES_TABLE = "note";

    public DatabaseHelper(android.content.Context context) {
        super(context, DATABASE_NAME, (android.database.sqlite.SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(android.database.sqlite.SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table " + NOTES_TABLE + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_TITLE + " text not null, " + COLUMN_CONTENT + " text not null, " + COLUMN_THICKNESS + " text not null);");
    }

    public void onUpgrade(android.database.sqlite.SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        onCreate(sQLiteDatabase);
    }

    public void insertNote(Note note) {
        android.database.sqlite.SQLiteDatabase writableDatabase = getWritableDatabase();
        android.content.ContentValues contentValues = new android.content.ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_THICKNESS, note.getThickness());
        writableDatabase.insert(NOTES_TABLE, null, contentValues);
    }

    public void deleteNote(Note note) {
        android.database.sqlite.SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = NOTES_TABLE;
        writableDatabase.delete(str, COLUMN_ID + " = ?", new String[]{String.valueOf(note.getId())});
    }

    public void updateNote(Note note) {
        android.database.sqlite.SQLiteDatabase writableDatabase = getWritableDatabase();
        android.content.ContentValues contentValues = new android.content.ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_THICKNESS, note.getThickness());
        String str = NOTES_TABLE;
        writableDatabase.update(str, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(note.getId())});
    }

    public java.util.List<Note> getAllNotes() {
        android.database.sqlite.SQLiteDatabase readableDatabase = getReadableDatabase();
        java.util.ArrayList arrayList = new java.util.ArrayList();
        android.database.Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM " + NOTES_TABLE, null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new Note(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public Note getSingleNote(int i) {
        android.database.sqlite.SQLiteDatabase readableDatabase = getReadableDatabase();
        android.database.Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM " + NOTES_TABLE + " WHERE " + COLUMN_ID + "=?", new String[]{String.valueOf(i)});
        Note note = (rawQuery == null || !rawQuery.moveToFirst()) ? null : new Note(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3));
        rawQuery.close();
        return note;
    }

    public int getNotesCount() {
        android.database.Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM " + NOTES_TABLE, null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }

    public java.util.List<Note> searchNote(String str) {
        android.database.sqlite.SQLiteDatabase readableDatabase = getReadableDatabase();
        java.util.ArrayList arrayList = new java.util.ArrayList();
        android.database.Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM " + NOTES_TABLE + " WHERE " + COLUMN_TITLE + " LIKE ?", new String[]{'%' + str + '%'});
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new Note(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }
}