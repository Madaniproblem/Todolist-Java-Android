package com.thamarezki.todolistproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String database = "Dbtodo";
    private static final int version = 6;
    public DBHelper(@Nullable Context context) {
        super(context,database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE actodo(id INTEGER PRIMARY KEY AUTOINCREMENT,planact TEXT,description TEXT,date TEXT,time TEXT,isDone INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS actodo");

    }

    public void insert_data(String planact,String description,String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("planact",planact);
        contentValues.put("description",description);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("isDone",0);

        db.insert("actodo",null,contentValues);
    }

    public Cursor cushow(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM actodo WHERE isDone = 0 ORDER BY ID DESC",null);
        return cursor;
    }

    public void delete_data (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("actodo","id=?",new String[]{String.valueOf(id)});
    }

    public int update_user (int id, String planact, String description, String date, String time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("planact",planact);
        values.put("description",description);
        values.put("date",date);
        values.put("time",time);
        return db.update("actodo",values,"id=?",new String[]{String.valueOf(id)});

    }

    public boolean Done(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isDone",1);
        int result = db.update("actodo",values,"id=?",new String[]{String.valueOf(id)});
        return result > 0;
    }

    public Cursor getDone() {
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("SELECT * FROM actodo WHERE isDone = 1 ORDER BY ID DESC",null);
    }

}
