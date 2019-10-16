package com.example.mark_5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ItemNoteActivity extends AppCompatActivity {

    public TextView textView;
    public EditText editText;

    public String item_name, note;
    private String items_DB = "save10.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_note);

        Bundle Intent_Data = getIntent().getExtras();
        item_name = Intent_Data.getString("Item_Name");
        note = Intent_Data.getString("Note");

        textView = findViewById(R.id.Item_Name_Header);
        editText = findViewById(R.id.Note_Field);

        textView.setText(item_name);
        editText.setText(note);

        BottomNavigationView Top_menu = findViewById(R.id.top_navigation_menu);
        Top_menu.setOnNavigationItemSelectedListener(top_navigation_menu);

        BottomNavigationView Bot_menu = findViewById(R.id.bottom_navigation_menu);
        Bot_menu.setOnNavigationItemSelectedListener(bot_navigation_menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener top_navigation_menu = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.return_back:
                    Intent intent = new Intent(ItemNoteActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
            }
            return true;
        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener bot_navigation_menu = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.ok_button_menu:
                    ContentValues Val = new ContentValues();
                    Val.put("Item_Notes", note);

                    // Поле заметок обновляется во всех строках, содержащих одинаковое название предмета
                    SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);
                    db.beginTransaction();

                    db.update("Monday_1", Val, "Item_Notes = " + item_name, null);
/*
                    db.update("Tuesday_1", Val, "Item_Notes = " + item_name, null);
                    db.update("Wednesday_1", Val, "Item_Notes = " + item_name, null);
                    db.update("Thursday_1", Val, "Item_Notes = " + item_name, null);
                    db.update("Friday_1", Val, "Item_Notes = " + item_name, null);
                    db.update("Saturday_1", Val, "Item_Notes = " + item_name, null);
                    db.update("Sunday_1", Val, "Item_Notes = " + item_name, null);

                    db.update("Monday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Tuesday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Wednesday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Thursday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Friday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Saturday_2", Val, "Item_Notes = " + item_name, null);
                    db.update("Sunday_2", Val, "Item_Notes = " + item_name, null);

 */

                    db.setTransactionSuccessful();
                    db.endTransaction();
                    db.close();

                    Intent intent = new Intent(ItemNoteActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
            }
            return true;
        }
    };



}
