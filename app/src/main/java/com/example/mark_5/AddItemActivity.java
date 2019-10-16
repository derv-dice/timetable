package com.example.mark_5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddItemActivity extends AppCompatActivity {

    public TextView t1, t2, t3, t4,t5, t6, t7, t8, t9;
    public String t1_str, t2_str, t3_str, t4_str, t5_str, t6_str, t7_str, t8_str, t9_str;
    private int Current_Day_Num = 0;
    private int Current_Week = 0;
    private String items_DB = "save10.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle Data_From_Last_Activity = getIntent().getExtras();
        Current_Day_Num = Data_From_Last_Activity.getInt("Day");
        Current_Week = Data_From_Last_Activity.getInt("Week");

        t1 = findViewById(R.id.editText4);
        t2 = findViewById(R.id.editText5);
        t3 = findViewById(R.id.editText6);
        t4 = findViewById(R.id.editText7);
        t5 = findViewById(R.id.editText8);
        t6 = findViewById(R.id.editText9);
        t7 = findViewById(R.id.editText10);
        t8 = findViewById(R.id.editText12);
        t9 = findViewById(R.id.editText11);

        BottomNavigationView Bot_Menu = findViewById(R.id.bottom_navigation_menu);
        Bot_Menu.setOnNavigationItemSelectedListener(bot_menu_listener);

        BottomNavigationView Top_menu = findViewById(R.id.top_navigation_menu);
        Top_menu.setOnNavigationItemSelectedListener(top_navigation_menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener top_navigation_menu = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.return_back:
                    Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
            }
            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener bot_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            t1_str = t1.getText().toString();
            t2_str = t2.getText().toString();
            t3_str = t3.getText().toString();
            t4_str = t4.getText().toString();
            t5_str = t5.getText().toString();
            t6_str = t6.getText().toString();
            t7_str = t7.getText().toString();
            t8_str = t8.getText().toString();
            t9_str = t9.getText().toString();

            ContentValues Val = new ContentValues();
            Val.put("Time0", t1_str);
            Val.put("Time1", t2_str);
            Val.put("Item_name", t3_str);
            Val.put("Teacher_name", t4_str);
            Val.put("Item_mode", t5_str);
            Val.put("Item_auditorium", t6_str);
            Val.put("Item_building", t7_str);

            Val.put("Teacher_Phone", t8_str);
            Val.put("Teacher_Mail", t9_str);

            Val.put("Favourite", "0");



            SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

            switch (Current_Week){
                case 1:   // ЧИСЛИТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            Val.put("Context_Table", "Monday_1");
                            db.beginTransaction();
                            db.insert("Monday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Monday_1", Val);
                            break;
                        case 2:
                            Val.put("Context_Table", "Tuesday_1");
                            db.beginTransaction();
                            db.insert("Tuesday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Tuesday_1", Val);
                            break;
                        case 3:
                            Val.put("Context_Table", "Wednesday_1");
                            db.beginTransaction();
                            db.insert("Wednesday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Wednesday_1", Val);
                            break;
                        case 4:
                            Val.put("Context_Table", "Thursday_1");
                            db.beginTransaction();
                            db.insert("Thursday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Thursday_1", Val);
                            break;
                        case 5:
                            Val.put("Context_Table", "Friday_1");
                            db.beginTransaction();
                            db.insert("Friday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Friday_1", Val);
                            break;
                        case 6:
                            Val.put("Context_Table", "Saturday_1");
                            db.beginTransaction();
                            db.insert("Saturday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Saturday_1", Val);
                            break;
                        case 7:
                            Val.put("Context_Table", "Sunday_1");
                            db.beginTransaction();
                            db.insert("Sunday_1", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Sunday_1", Val);
                            break;
                    }
                    break;
                case 2:   // ЗНАМЕНАТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            Val.put("Context_Table", "Monday_2");
                            db.beginTransaction();
                            db.insert("Monday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Monday_1", Val);
                            break;
                        case 2:
                            Val.put("Context_Table", "Tuesday_2");
                            db.beginTransaction();
                            db.insert("Tuesday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Tuesday_1", Val);
                            break;
                        case 3:
                            Val.put("Context_Table", "Wednesday_2");
                            db.beginTransaction();
                            db.insert("Wednesday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Wednesday_1", Val);
                            break;
                        case 4:
                            Val.put("Context_Table", "Thursday_2");
                            db.beginTransaction();
                            db.insert("Thursday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Thursday_1", Val);
                            break;
                        case 5:
                            Val.put("Context_Table", "Friday_2");
                            db.beginTransaction();
                            db.insert("Friday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Friday_1", Val);
                            break;
                        case 6:
                            Val.put("Context_Table", "Saturday_2");
                            db.beginTransaction();
                            db.insert("Saturday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Saturday_1", Val);
                            break;
                        case 7:
                            Val.put("Context_Table", "Sunday_2");
                            db.beginTransaction();
                            db.insert("Sunday_2", null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //DB.add_item_to_db(items_DB, "Sunday_1", Val);
                            break;
                    }
                    break;
            }

            Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            Toast.makeText(getBaseContext(), "Предмет добавлен", Toast.LENGTH_LONG).show();

            return true;
        }
    };

}
