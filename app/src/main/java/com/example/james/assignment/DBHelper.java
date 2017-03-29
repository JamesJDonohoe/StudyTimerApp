package com.example.james.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;


/**
 *DBHelper class, this class creates the database and has all the functionality that the database requires.
 *Each activity can call this class and call the methods within this class.
 */

class DBHelper extends SQLiteOpenHelper {

    //DB name
    private static final String DATABASE_NAME = "study.db";
    //Table Name
    private static final String TABLE_NAME = "study_table";
    private static final String TABLE_NAME2 = "time_table";
    //Columns
    private static final String COL_1 = "ID";
    private static final String COL_2 = "is_study";
    //This column is in table_name2
    private static final String COL_3 = "TIME";


    DBHelper(Context context) {

        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Creating the tables and the rows within them
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the table, giving it the variables
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "is_study TEXT )");
        db.execSQL("create table " + TABLE_NAME2 +" (TIME VARCHAR2 PRIMARY KEY )");
    }

    //Dropping the table if it exits
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    //Getting data from the user
    boolean insertData(String is_study){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, is_study);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result== - 1)
            return false;
        else
            return true;
    }

    //Getting time from database
    boolean insertTime(String TIME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_3, TIME);
        long result1 = db.insert(TABLE_NAME2, null, contentValues2);

        if (result1== - 1)
            return false;
        else
            return true;
    }

    //Cursor to display data the user entered
    Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
        return data;
    }

    //Cursor to display time saved
    Cursor getTime(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dataTime = db.rawQuery("SELECT TIME FROM " +TABLE_NAME2, null);
        return dataTime;
    }

    //Takes the ID of the data and the corresponding data name to Update the text
    boolean updateData(String id, String study){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, study);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;
    }

    //Takes the ID so the data can be deleted
    Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }

    //Deletes the time from TABLE_NAME2
    Integer deleteTime(long TIME){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "TIME = ?", new String[] {String.valueOf(TIME)});
    }

}
