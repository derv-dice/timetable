package com.example.mark_5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridListAdapter Main_adapter;
    private ListView Main_ListView;
    private int Current_Day_Num = 1;
    private int Week = 1;
    private Button Week_Btn;
    public ArrayList<Schedule_Item> DB_Items_list;


    private int Selected_Item_Position = 0;
    private BottomNavigationView edit_mode_menu;

    private ItemsDataBase DB;
    private String items_DB = "save8.db";
    private ArrayList<ScheduleItem> Main_Array_List;
    private ScheduleListAdapter Main_Array_List_Adapter;

    @Override
    protected void onResume()
    {
        super.onResume();
        update_Main_ListView(Week,Current_Day_Num);
        //Update_List_via_Database(Week,Current_Day_Num);
    }

    private void first_create_table(String db_name, String table_name){
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + " (Object_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Time0 TEXT, Time1 TEXT, Item_name TEXT, Teacher_name TEXT, Item_mode TEXT, " +
                "Item_auditorium TEXT, Item_building TEXT, Teacher_Phone TEXT, " +
                "Teacher_Mail TEXT, Favourite TEXT)");
        db.close();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_create_table(items_DB, "Monday_1");
        first_create_table(items_DB, "Tuesday_1");
        first_create_table(items_DB, "Wednesday_1");
        first_create_table(items_DB, "Thursday_1");
        first_create_table(items_DB, "Friday_1");
        first_create_table(items_DB, "Saturday_1");
        first_create_table(items_DB, "Sunday_1");
        first_create_table(items_DB, "Monday_2");
        first_create_table(items_DB, "Tuesday_2");
        first_create_table(items_DB, "Wednesday_2");
        first_create_table(items_DB, "Thursday_2");
        first_create_table(items_DB, "Friday_2");
        first_create_table(items_DB, "Saturday_2");
        first_create_table(items_DB, "Sunday_2");

        BottomNavigationView Top_Menu = findViewById(R.id.top_navigation_menu); // Верхние и нижние MenuBar
        Top_Menu.setOnNavigationItemSelectedListener(top_menu_listener);

        BottomNavigationView Bot_Menu = findViewById(R.id.bottom_navigation_menu);
        Bot_Menu.setOnNavigationItemSelectedListener(bot_menu_listener);

        BottomNavigationView Edit_Menu = findViewById(R.id.edit_mode_menu);
        Bot_Menu.setOnNavigationItemSelectedListener(edit_menu_listener);

        edit_mode_menu = findViewById(R.id.edit_mode_menu);
        edit_mode_menu.setVisibility(View.INVISIBLE);
        edit_mode_menu.setEnabled(false);

        update_Main_ListView(Week, Current_Day_Num);

        Main_ListView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeRight() {
                Day_Change_Right();
            }
            public void onSwipeLeft() {
                Day_Change_Left();
            }
        });

        Main_ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, String.valueOf(position) , Toast.LENGTH_LONG).show();

                Selected_Item_Position = position;

                edit_mode_menu.setEnabled(true);
                edit_mode_menu.setVisibility(View.VISIBLE);

                return true;
            }
        });

    }



    @Override
    public void onBackPressed() {
        if (edit_mode_menu.isEnabled()){
            edit_mode_menu.setEnabled(false);
            edit_mode_menu.setVisibility(View.INVISIBLE);
        }
        else{
            finish();
        }
    }

    private void Week_Change_Swipe(){
        Week_Btn = findViewById(R.id.Week_Stage);
        if (Week == 1){
            Week = 2;
            Week_Btn.setText(R.string.Week_2);
        }
        else{
            Week = 1;
            Week_Btn.setText(R.string.Week_1);
        }
        update_Main_ListView(Week, Current_Day_Num);
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
        update_Main_ListView(Week, Current_Day_Num);

    }

    public void update_Main_ListView(Integer Week, Integer Day){
        Main_ListView = findViewById(R.id.List);
        Main_Array_List = new ArrayList<ScheduleItem>();
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

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

        if(cursor.moveToFirst()){
            do{
                String Object_Id = cursor.getString(0);
                String Time0 = cursor.getString(1);
                String Time1 = cursor.getString(2);
                String Item_name = cursor.getString(3);
                String Teacher_name = cursor.getString(4);
                String Item_mode = cursor.getString(5);
                String Item_auditorium = cursor.getString(6);
                String Item_building = cursor.getString(7);

                String Teacher_Phone = cursor.getString(8);
                String Teacher_Mail = cursor.getString(9);
                String Favourite = cursor.getString(10);

                Main_Array_List.add(new ScheduleItem(Object_Id, Time0, Time1, Item_name, Teacher_name, Item_mode, Item_auditorium, Item_building, Teacher_Phone, Teacher_Mail, Favourite));
            }
            while(cursor.moveToNext());
        }
        Main_Array_List_Adapter = new ScheduleListAdapter(MainActivity.this, R.layout.list_item_layout_v2, Main_Array_List);
        Main_ListView.setAdapter(Main_Array_List_Adapter);
        check_day_for_empty(cursor.getCount());

        cursor.close();
        db.close();
    }

    private void check_day_for_empty(int cursor_count){
        TextView If_Day_Is_Empty = findViewById(R.id.Empty_Day_Text);
        if (cursor_count > 0){
            If_Day_Is_Empty.setAlpha(0.0f);
        }
        else {
            If_Day_Is_Empty.setAlpha(1.0f);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener edit_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.edit_button:

                    break;
                case R.id.delete_button:

                    break;
            }
            return true;
        }
    };


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
                case R.id.settings_button:

                    break;
                case R.id.menu_button:

                    break;
            }
            return true;
        }
    };

    public void Day_Change_Right(){
        TextView Header = findViewById(R.id.day_of_the_week);
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
        update_Main_ListView(Week,Current_Day_Num);
    }

    public void Day_Change_Left(){
        TextView Header = findViewById(R.id.day_of_the_week);
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
        update_Main_ListView(Week,Current_Day_Num);
        //Update_List_via_Database(Week,Current_Day_Num);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener top_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            TextView Header = findViewById(R.id.day_of_the_week);

            switch (menuItem.getItemId()){
                case R.id.left_arrow_button:
                    Day_Change_Right();
                    break;

                case R.id.right_arrow_button:
                    Day_Change_Left();
                    break;
            }
            return true;
        }
    };
}
