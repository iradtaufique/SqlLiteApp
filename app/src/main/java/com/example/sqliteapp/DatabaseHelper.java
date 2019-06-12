package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DaTABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String Colum_1 = "ID";
    public static final String Colum_2 = "NAME";
    public static final String Colum_3 = "SIRNAME";
    public static final String Colum_4 = "MARKS";


/* creation of constructor databasehelper when it is colled the database will be created */
    public DatabaseHelper(Context context) {
        super(context, DaTABASE_NAME, null, 1);

}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table " +TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SIRNAME TEXT, MARKS TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String sirname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Colum_2,name);
        contentValues.put(Colum_3,sirname);
        contentValues.put(Colum_4,marks);
        long results = db.insert(TABLE_NAME,null, contentValues);
        if (results == -1){
            return false;
        }
        else
           return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
}
