package com.example.mark_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Add_new_Item extends AppCompatActivity {

    public TextView t1, t2, t3, t4,t5, t6, t7;
    public String t1_str, t2_str, t3_str, t4_str, t5_str, t6_str, t7_str;
    private int Current_Day_Num = 0;
    private int Current_Week = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_new__item);

        Bundle Data_From_Last_Activity = getIntent().getExtras();;
        Current_Day_Num = Data_From_Last_Activity.getInt("Day");
        Current_Week = Data_From_Last_Activity.getInt("Week");

        t1 = findViewById(R.id.editText4);
        t2 = findViewById(R.id.editText5);
        t3 = findViewById(R.id.editText6);
        t4 = findViewById(R.id.editText7);
        t5 = findViewById(R.id.editText8);
        t6 = findViewById(R.id.editText9);
        t7 = findViewById(R.id.editText10);

        BottomNavigationView Bot_Menu = findViewById(R.id.bot_menu_add_fragment);
        Bot_Menu.setOnNavigationItemSelectedListener(bot_menu_listener);
    }


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

            ContentValues Val = new ContentValues();
            Val.put("time0", t1_str);
            Val.put("time1", t2_str);
            Val.put("item_name", t3_str);
            Val.put("teacher_name", t4_str);
            Val.put("item_mode", t5_str);
            Val.put("item_auditorium", t6_str);
            Val.put("item_building", t7_str);

            SQLiteDatabase db = openOrCreateDatabase("save_7.db", MODE_PRIVATE, null);
            switch (Current_Week){
                case 1:   // ЧИСЛИТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            db.beginTransaction();
                            db.insert("Monday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 2:
                            db.beginTransaction();
                            db.insert("Tuesday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 3:
                            db.beginTransaction();
                            db.insert("Wednesday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 4:
                            db.beginTransaction();
                            db.insert("Thursday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 5:
                            db.beginTransaction();
                            db.insert("Friday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 6:
                            db.beginTransaction();
                            db.insert("Saturday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 7:
                            db.beginTransaction();
                            db.insert("Sunday_1",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                    }
                    break;
                case 2:   // ЗНАМЕНАТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            db.beginTransaction();
                            db.insert("Monday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 2:
                            db.beginTransaction();
                            db.insert("Tuesday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 3:
                            db.beginTransaction();
                            db.insert("Wednesday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 4:
                            db.beginTransaction();
                            db.insert("Thursday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 5:
                            db.beginTransaction();
                            db.insert("Friday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 6:
                            db.beginTransaction();
                            db.insert("Saturday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 7:
                            db.beginTransaction();
                            db.insert("Sunday_2",null, Val);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                    }
                break;
            }

            db.close();

            Intent intent = new Intent(Activity_Add_new_Item.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            Toast.makeText(getBaseContext(), "Предмет добавлен", Toast.LENGTH_LONG).show();

            return true;
        }
    };

}
