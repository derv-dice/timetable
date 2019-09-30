package com.example.mark_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private int Current_Day_Num = 1;
    private int Week = 1;
    private Button Week_Btn;

    public ArrayList<Schedule_Item> Schedule_Items_List = new ArrayList<Schedule_Item>();

    @Override
    protected void onResume()
    {
        super.onResume();
        Update_List_via_Database(Week,Current_Day_Num);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("save_6.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Monday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Tuesday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Thursday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Friday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Saturday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Sunday_1 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Monday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Tuesday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Thursday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Friday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Saturday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Sunday_2 (time0 TEXT, time1 TEXT, item_name TEXT, teacher_name TEXT, item_mode TEXT, item_auditorium TEXT, item_building TEXT)");
            db.close();
        }// Создание базы данных, если ее нет

        BottomNavigationView Top_Menu = findViewById(R.id.top_navigation_menu); // Верхние и нижние MenuBar
        Top_Menu.setOnNavigationItemSelectedListener(top_menu_listener);
        BottomNavigationView Bot_Menu = findViewById(R.id.bottom_navigation_menu);
        Bot_Menu.setOnNavigationItemSelectedListener(bot_menu_listener);

        Update_List_via_Database(Week,Current_Day_Num);
    }

    public void Change_Week_Stage(View view){
        Week_Btn = findViewById(R.id.Week_Stage);
        if (Week == 1){
            Week = 2;
            Week_Btn.setText(R.string.Week_2);
        }
        else{
            Week = 1;
            Week_Btn.setText(R.string.Week_1);
        }
        Update_List_via_Database(Week,Current_Day_Num);
    }

    public void Update_List_via_Database (Integer Week, Integer Day){
        ListView Main_List_View = findViewById(R.id.List);
        ArrayList<Schedule_Item> DB_Items_list = new ArrayList<>();
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("save_6.db", MODE_PRIVATE, null);
        Cursor cursor = null;

        switch (Week){  // В зависимости от текущей недели и дня недели выбирается нужная таблица из базы данных save_n.db
            case 1:
                switch (Day){
                    case 1:
                        cursor = db.rawQuery("SELECT * FROM Monday_1;",null);
                        break;
                    case 2:
                        cursor = db.rawQuery("SELECT * FROM Tuesday_1;", null);
                        break;
                    case 3:
                        cursor = db.rawQuery("SELECT * FROM Wednesday_1;", null);
                        break;
                    case 4:
                        cursor = db.rawQuery("SELECT * FROM Thursday_1;", null);
                        break;
                    case 5:
                        cursor = db.rawQuery("SELECT * FROM Friday_1;", null);
                        break;
                    case 6:
                        cursor = db.rawQuery("SELECT * FROM Saturday_1;", null);
                        break;
                    case 7:
                        cursor = db.rawQuery("SELECT * FROM Sunday_1;", null);
                        break;
                }
                break;
            case 2:
                switch (Day){
                    case 1:
                        cursor = db.rawQuery("SELECT * FROM Monday_2;", null);
                        break;
                    case 2:
                        cursor = db.rawQuery("SELECT * FROM Tuesday_2;", null);
                        break;
                    case 3:
                        cursor = db.rawQuery("SELECT * FROM Wednesday_2;", null);
                        break;
                    case 4:
                        cursor = db.rawQuery("SELECT * FROM Thursday_2;", null);
                        break;
                    case 5:
                        cursor = db.rawQuery("SELECT * FROM Friday_2;", null);
                        break;
                    case 6:
                        cursor = db.rawQuery("SELECT * FROM Saturday_2;", null);
                        break;
                    case 7:
                        cursor = db.rawQuery("SELECT * FROM Sunday_2;", null);
                        break;
                }
                break;
        }

        TextView If_Day_Is_Empty = findViewById(R.id.Empty_Day_Text);

        if(cursor.moveToFirst()){
            do{
                String time0 = cursor.getString(0);
                String time1 = cursor.getString(1);
                String item_name = cursor.getString(2);
                String teacher_name = cursor.getString(3);
                String item_mode = cursor.getString(4);
                String item_auditorium = cursor.getString(5);
                String item_building = cursor.getString(6);

                DB_Items_list.add(new Schedule_Item(time0, time1, item_name, teacher_name, item_mode, item_auditorium, item_building));
            }
            while(cursor.moveToNext());
        }
        else{
            DB_Items_list.clear();
        }

        GridListAdapter new_adapter = new GridListAdapter(MainActivity.this, DB_Items_list);
        Main_List_View.setAdapter(new_adapter);
        new_adapter.notifyDataSetChanged();

        if (cursor.getCount() > 0){
            If_Day_Is_Empty.setAlpha(0.0f);
        }
        else {
            If_Day_Is_Empty.setAlpha(1.0f);
        }

        cursor.close();
        db.close();
    }





    private BottomNavigationView.OnNavigationItemSelectedListener bot_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case  R.id.add_button: // Добавление строки в список предметов
                    Intent intent = new Intent(MainActivity.this, Activity_Add_new_Item.class);

                    intent.putExtra("Day", Current_Day_Num);
                    intent.putExtra("Week", Week);

                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    break;
                case R.id.trash_button: // Удаление строки

                    break;
                case R.id.settings_button:

                    break;
                case R.id.menu_button:

                    break;
            }
            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener top_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            TextView Header = findViewById(R.id.day_of_the_week);
            ListView Main_List = (ListView) findViewById(R.id.List);

            switch (menuItem.getItemId()){
                case R.id.left_arrow_button:
                    switch (Header.getText().toString()){
                        case "Понедельник":
                            Header.setText("Воскресенье");
                            Current_Day_Num = 7;

                            break;
                        case "Воскресенье":
                            Header.setText("Суббота");
                            Current_Day_Num = 6;

                            break;
                        case "Суббота":
                            Header.setText("Пятница");
                            Current_Day_Num = 5;

                            break;
                        case "Пятница":
                            Header.setText("Четверг");
                            Current_Day_Num = 4;

                            break;
                        case "Четверг":
                            Header.setText("Среда");
                            Current_Day_Num = 3;

                            break;
                        case "Среда":
                            Header.setText("Вторник");
                            Current_Day_Num = 2;

                            break;
                        case "Вторник":
                            Header.setText("Понедельник");
                            Current_Day_Num = 1;

                            break;
                    }
                    break;

                case R.id.right_arrow_button:
                    switch (Header.getText().toString()){
                        case "Понедельник":
                            Header.setText("Вторник");
                            Current_Day_Num = 2;

                            break;
                        case "Вторник":
                            Header.setText("Среда");
                            Current_Day_Num = 3;

                            break;
                        case "Среда":
                            Header.setText("Четверг");
                            Current_Day_Num = 4;

                            break;
                        case "Четверг":
                            Header.setText("Пятница");
                            Current_Day_Num = 5;

                            break;
                        case "Пятница":
                            Header.setText("Суббота");
                            Current_Day_Num = 6;

                            break;
                        case "Суббота":
                            Header.setText("Воскресенье");
                            Current_Day_Num = 7;

                            break;
                        case "Воскресенье":
                            Header.setText("Понедельник");
                            Current_Day_Num = 1;

                            break;
                    }
                    break;
            }
            Update_List_via_Database(Week,Current_Day_Num);
            return true;
        }
    };
}
