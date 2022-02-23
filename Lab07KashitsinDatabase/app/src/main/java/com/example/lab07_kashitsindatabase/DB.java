package com.example.lab07_kashitsindatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE testDB (keyDB TEXT PRIMARY KEY, valueDB TEXT);";
        db.execSQL(sql);
    }

    public void Insert(String keyDB, String valueDB)//Кашицын,393
    {
        String sql = "INSERT INTO testDB VALUES('" + keyDB + "', '" + valueDB + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void Update(String keySelect, String value)
    {
        String sql = "UPDATE testDB SET valueDB = '" + value + "' WHERE keyDB = '"+ keySelect + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void Delete(String keySelect)
    {
        String sql = "DELETE FROM testDB WHERE keyDB = '"+ keySelect + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String Select(String keySelect)//Кашицын,393
    {
        String sql = "SELECT valueDB FROM testDB WHERE keyDB = '" + keySelect + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == true)
            return cursor.getString(0);
        return "(!) Не найдено";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
