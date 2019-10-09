package com.example.mark_5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDataBase {


    public void create_table__if_not_exist(String DB_name, String table_name) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + " (Object_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Time0 TEXT, Time1 TEXT, Item_name TEXT, Teacher_name TEXT, Item_mode TEXT, " +
                "Item_auditorium TEXT, Item_building TEXT, Teacher_Phone TEXT, " +
                "Teacher_Mail TEXT, Favourite TEXT)");
        db.close();
    }

    public void create_items_db(String DB_name) {
        create_table__if_not_exist(DB_name, "Monday_1");
        create_table__if_not_exist(DB_name, "Tuesday_1");
        create_table__if_not_exist(DB_name, "Wednesday_1");
        create_table__if_not_exist(DB_name, "Thursday_1");
        create_table__if_not_exist(DB_name, "Friday_1");
        create_table__if_not_exist(DB_name, "Saturday_1");
        create_table__if_not_exist(DB_name, "Sunday_1");

        create_table__if_not_exist(DB_name, "Monday_2");
        create_table__if_not_exist(DB_name, "Tuesday_2");
        create_table__if_not_exist(DB_name, "Wednesday_2");
        create_table__if_not_exist(DB_name, "Thursday_2");
        create_table__if_not_exist(DB_name, "Friday_2");
        create_table__if_not_exist(DB_name, "Saturday_2");
        create_table__if_not_exist(DB_name, "Sunday_2");
    }

    public void add_item_to_db(String DB_name, String table_name, String Time0, String Time1,
                               String Item_name, String Teacher_name, String Item_mode,
                               String Item_auditorium, String Item_building,
                               String Teacher_Phone, String Teacher_Mail, String Favourite) {
        ContentValues Val = new ContentValues();

        Val.put("Time0", Time0);
        Val.put("Time1", Time1);
        Val.put("Item_name", Item_name);
        Val.put("Teacher_name", Teacher_name);
        Val.put("Item_mode", Item_mode);
        Val.put("Item_auditorium", Item_auditorium);
        Val.put("Item_building", Item_building);

        Val.put("Teacher_Phone", Teacher_Phone);
        Val.put("Teacher_Mail", Teacher_Mail);
        Val.put("Favourite", Favourite);

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.beginTransaction();
        db.insert(table_name, null, Val);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void delete_item_from_db(String DB_name, String table_name, String row_ID) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_name, null);
        db.execSQL("DELETE FROM "+table_name+" WHERE Object_Id = " + row_ID);
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
}
