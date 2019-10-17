package com.example.mark_5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ItemNoteActivity extends AppCompatActivity {

    public TextView textView;
    public EditText editText;

    public String item_name, note;
    private String items_DB = "save11.db";

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

                    note = editText.getText().toString();

                    ContentValues Val = new ContentValues();
                    Val.put("Item_Notes", note);

                    // Поле заметок обновляется во всех строках, содержащих одинаковое название предмета
                    SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

                    String[] Temp_Array = {"Monday_1", "Tuesday_1", "Wednesday_1", "Thursday_1", "Friday_1", "Saturday_1", "Sunday_1",
                            "Monday_2", "Tuesday_2", "Wednesday_2", "Thursday_2", "Friday_2", "Saturday_2", "Sunday_2"};

                    try {
                        for (int i = 0; i < 13; i++) {
                            db.beginTransaction();
                            db.update(Temp_Array[i], Val, "Item_name = '" + item_name + "'", null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } finally {
                        db.close();
                    }

                    Intent intent = new Intent(ItemNoteActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
            }
            return true;
        }
    };


}
