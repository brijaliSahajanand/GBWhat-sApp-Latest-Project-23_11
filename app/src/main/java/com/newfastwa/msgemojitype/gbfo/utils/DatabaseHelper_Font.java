package com.newfastwa.msgemojitype.gbfo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.newfastwa.msgemojitype.gbfo.model.Pojo;

import java.util.ArrayList;


public class DatabaseHelper_Font extends SQLiteOpenHelper {
    public static final String DBNAME = "db.sqlite";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        sQLiteDatabase.disableWriteAheadLogging();
    }

    public DatabaseHelper_Font(Context context) {
        super(context, DBNAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
    }

    public void openDatabase() {
        String path = this.mContext.getDatabasePath(DBNAME).getPath();
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            this.mDatabase = SQLiteDatabase.openDatabase(path, (SQLiteDatabase.CursorFactory) null, 0);
        }
    }

    public void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public ArrayList<String> getDecorations() {
        openDatabase();
        Cursor rawQuery = this.mDatabase.rawQuery("SELECT * FROM art", (String[]) null);
        rawQuery.moveToFirst();
        ArrayList<String> arrayList = new ArrayList<>();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(rawQuery.getString(1));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        closeDatabase();
        return arrayList;
    }

    public Pojo getSymbols() {
        openDatabase();
        Cursor rawQuery = this.mDatabase.rawQuery("SELECT * FROM symbols", (String[]) null);
        rawQuery.moveToFirst();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        ArrayList arrayList12 = new ArrayList();
        while (!rawQuery.isAfterLast()) {
            if (rawQuery.getString(0) != null && !rawQuery.getString(0).equals("")) {
                arrayList.add(rawQuery.getString(0));
            }
            if (rawQuery.getString(1) != null && !rawQuery.getString(1).equals("")) {
                arrayList2.add(rawQuery.getString(1));
            }
            if (rawQuery.getString(2) != null && !rawQuery.getString(2).equals("")) {
                arrayList3.add(rawQuery.getString(2));
            }
            if (rawQuery.getString(3) != null && !rawQuery.getString(3).equals("")) {
                arrayList4.add(rawQuery.getString(3));
            }
            if (rawQuery.getString(4) != null && !rawQuery.getString(4).equals("")) {
                arrayList5.add(rawQuery.getString(4));
            }
            if (rawQuery.getString(5) != null && !rawQuery.getString(5).equals("")) {
                arrayList6.add(rawQuery.getString(5));
            }
            if (rawQuery.getString(6) != null && !rawQuery.getString(6).equals("")) {
                arrayList7.add(rawQuery.getString(6));
            }
            if (rawQuery.getString(7) != null && !rawQuery.getString(7).equals("")) {
                arrayList8.add(rawQuery.getString(7));
            }
            if (rawQuery.getString(8) != null && !rawQuery.getString(8).equals("")) {
                arrayList9.add(rawQuery.getString(8));
            }
            if (rawQuery.getString(9) != null && !rawQuery.getString(9).equals("")) {
                arrayList10.add(rawQuery.getString(9));
            }
            if (rawQuery.getString(10) != null && !rawQuery.getString(10).equals("")) {
                arrayList11.add(rawQuery.getString(10));
            }
            if (rawQuery.getString(11) != null && !rawQuery.getString(11).equals("")) {
                arrayList12.add(rawQuery.getString(11));
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        closeDatabase();
        return new Pojo(arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7, arrayList8, arrayList9, arrayList10, arrayList11, arrayList12);
    }

    public ArrayList<ArrayList<String>> getTextStyles() {
        openDatabase();
        Cursor rawQuery = this.mDatabase.rawQuery("SELECT * FROM fonts", (String[]) null);
        rawQuery.moveToFirst();
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (rawQuery.isAfterLast()) {
                break;
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i = 1; i < rawQuery.getColumnCount(); i++) {
                arrayList2.add(rawQuery.getString(i));
            }
            arrayList.add(arrayList2);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        closeDatabase();
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < ((ArrayList) arrayList.get(0)).size(); i2++) {
            ArrayList arrayList4 = new ArrayList();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList4.add(((ArrayList) arrayList.get(i3)).get(i2));
            }
            arrayList3.add(arrayList4);
        }
        ArrayList<ArrayList<String>> arrayList5 = new ArrayList<>();
        arrayList5.add((ArrayList<String>) arrayList3.get(0));
        arrayList5.add((ArrayList<String>) arrayList3.get(4));
        arrayList5.add((ArrayList<String>) arrayList3.get(1));
        arrayList5.add((ArrayList<String>) arrayList3.get(79));
        arrayList5.add((ArrayList<String>) arrayList3.get(86));
        arrayList5.add((ArrayList<String>) arrayList3.get(82));
        arrayList5.add((ArrayList<String>) arrayList3.get(31));
        arrayList5.add((ArrayList<String>) arrayList3.get(44));
        for (int i4 = 0; i4 < arrayList3.size(); i4++) {
            if (!(i4 == 0 || i4 == 4 || i4 == 1 || i4 == 79 || i4 == 86 || i4 == 82 || i4 == 31 || i4 == 44)) {
                arrayList5.add((ArrayList<String>) arrayList3.get(i4));
            }
        }
        return arrayList5;
    }
}
