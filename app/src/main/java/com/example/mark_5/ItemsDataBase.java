package com.example.mark_5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDataBase {

    public void add_item_to_db(String DB_name, String table_name, ContentValues Val) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.beginTransaction();
        db.insert(table_name, null, Val);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void delete_item_from_db(String DB_name, String table_name, String row_ID) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("DELETE FROM " + table_name + " WHERE Object_Id = " + row_ID);
        db.close();
    }

    public void edit_item_from_db(String DB_name, String table_name, String row_ID, String Time0,
                                  String Time1, String Item_name, String Teacher_name,
                                  String Item_mode, String Item_auditorium, String Item_building,
                                  String Teacher_Phone, String Teacher_Mail, String Favourite){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("UPDATE " + table_name + " SET Time0 = " + Time0 + ", Time1 = " + Time1 + "," +
                " Item_name = " + Item_name + ", Teacher_name = " + Teacher_name +
                ", Item_mode = " + Item_mode + ", Item_auditorium = " + Item_auditorium +
                ", Item_building = " + Item_building + ", Teacher_Phone = " + Teacher_Phone +
                ", Teacher_Mail = " + Teacher_Mail + ", Favourite = " + Favourite +
                "WHERE Object_Id = " + row_ID);
        db.close();
    }

    public void set_favourite(String DB_name, String table_name, String row_ID){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("UPDATE " + table_name + " SET Favourite = '1' WHERE Object_Id = " + row_ID);
        db.close();
    }

    public void disable_favourite(String DB_name, String table_name, String row_ID){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("UPDATE " + table_name + " SET Favourite = '0' WHERE Object_Id = " + row_ID);
        db.close();
    }

    public Cursor get__all_data_from_table(String DB_name, String table_name){
        Cursor cursor = null;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        cursor = db.rawQuery("SELECT * FROM " + table_name +";",null);
        cursor.close();
        db.close();
        return cursor;
    }

    public Cursor get__single_row_from_table(String DB_name, String table_name, String row_ID){
        Cursor cursor = null;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        cursor = db.rawQuery("SELECT * FROM " + table_name +" WHERE Object_Id = " + row_ID + ";",null);
        cursor.close();
        db.close();
        return cursor;
    }
}
