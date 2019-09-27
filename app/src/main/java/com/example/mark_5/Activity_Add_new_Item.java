package com.example.mark_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Add_new_Item extends AppCompatActivity {

    TextView t1, t2, t3, t4,t5,t6,t7;
    String t1_str, t2_str, t3_str, t4_str, t5_str, t6_str, t7_str;
    String Current_Day_Num = "hui";
    String Current_Week = "hui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_new__item);

        {
            t1 = (TextView) findViewById(R.id.editText4);
            t2 = (TextView) findViewById(R.id.editText5);
            t3 = (TextView) findViewById(R.id.editText6);
            t4 = (TextView) findViewById(R.id.editText7);
            t5 = (TextView) findViewById(R.id.editText8);
            t6 = (TextView) findViewById(R.id.editText9);
            t7 = (TextView) findViewById(R.id.editText10);
        }

        Bundle Data_From_Last_Activity;
        Data_From_Last_Activity = getIntent().getExtras();
        if (Data_From_Last_Activity != null){
            Current_Day_Num = Data_From_Last_Activity.get("Day").toString();
            Current_Week = Data_From_Last_Activity.get("Week").toString();
        }

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


            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("save_2.db", MODE_PRIVATE, null);

            switch (Current_Week){
                case "1":   // ЧИСЛИТЕЛЬ
                    switch (Current_Day_Num){
                        case "1":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Monday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Monday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "2":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Tuesday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Tuesday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "3":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Wednesday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "4":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Thursday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Thursday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "5":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Friday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Friday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "6":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Saturday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Saturday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "7":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Sunday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Sunday_1 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                    }

                case "2":   // ЗНАМЕНАТЕЛЬ
                    switch (Current_Day_Num){
                        case "1":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Monday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Monday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "2":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Tuesday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Tuesday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "3":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Wednesday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "4":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Thursday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Thursday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "5":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Friday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Friday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "6":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Saturday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Saturday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                        case "7":
                            db.execSQL("CREATE TABLE IF NOT EXISTS Sunday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
                            db.execSQL("INSERT INTO Sunday_2 VALUES (" + t1_str +"," + t2_str + "," + t3_str + "," + t4_str + "," + t5_str + "," + t6_str + "," + t7_str + ");");
                            break;
                    }

                break;
            }

            db.close();

            Intent intent = new Intent(Activity_Add_new_Item.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            return true;
        }
    };

}
