package com.example.mark_5;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences settings;

    private ListView Main_ListView;
    private int Current_Day_Num = 1;
    private int Week = 1;
    private Button Week_Btn;

    private BottomNavigationView edit_mode_menu;

    private String items_DB = "save11.db";
    private ArrayList<ScheduleItem> Main_Array_List;
    private ScheduleListAdapter_flat Main_Array_List_Adapter;
    private String current_row_id = "0";
    private Button patch;
    private boolean curent_patch = false;

    private AlertDialog.Builder MessageBox;

    Animation alpha_show;
    Animation alpha_hide;

    @Override
    protected void onResume() {
        super.onResume();
        update_Main_ListView(Week, Current_Day_Num);
        edit_mode_menu.setEnabled(false);
        edit_mode_menu.setVisibility(View.INVISIBLE);
        patch.setVisibility(View.INVISIBLE);
    }

    private void first_create_table(String db_name, String table_name) {
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + " (Object_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Time0 TEXT, Time1 TEXT, Item_name TEXT, Teacher_name TEXT, Item_mode TEXT, " +
                "Item_auditorium TEXT, Item_building TEXT, Teacher_Phone TEXT, " +
                "Teacher_Mail TEXT, Favourite TEXT, Context_Table TEXT, Item_Notes TEXT)");
        db.close();
    }

    void show_edit_menu() {
        BottomNavigationView edit_menu = findViewById(R.id.edit_mode_menu);
        BottomNavigationView main_menu = findViewById(R.id.bottom_navigation_menu);

        edit_menu.setEnabled(true);
        edit_menu.setVisibility(View.VISIBLE);
        edit_menu.startAnimation(alpha_show);
    }

    void hide_edit_menu() {
        BottomNavigationView edit_menu = findViewById(R.id.edit_mode_menu);
        BottomNavigationView main_menu = findViewById(R.id.bottom_navigation_menu);

        if (curent_patch == true) edit_menu.startAnimation(alpha_hide);
        edit_menu.setVisibility(View.INVISIBLE);
        edit_menu.setEnabled(false);
    }

    void move_list_from_left() {
        Animation from_left_slide = AnimationUtils.loadAnimation(this, R.anim.move_from_left);
        Main_ListView.setVisibility(View.VISIBLE);
        Main_ListView.startAnimation(from_left_slide);
    }

    void move_list_to_left() {
        Animation to_left_slide = AnimationUtils.loadAnimation(this, R.anim.move_to_left);
        Main_ListView.startAnimation(to_left_slide);
        Main_ListView.setVisibility(View.INVISIBLE);
    }

    void move_list_from_right() {
        Animation from_right_slide = AnimationUtils.loadAnimation(this, R.anim.move_from_right);
        Main_ListView.setVisibility(View.VISIBLE);
        Main_ListView.startAnimation(from_right_slide);
    }

    void move_list_to_right() {
        Animation to_right_slide = AnimationUtils.loadAnimation(this, R.anim.move_to_right);
        Main_ListView.startAnimation(to_right_slide);
        Main_ListView.setVisibility(View.INVISIBLE);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences("App_Settings", MODE_PRIVATE);

        patch = findViewById(R.id.patch);
        patch.setVisibility(View.INVISIBLE);

        alpha_hide = AnimationUtils.loadAnimation(this, R.anim.alpha_hide);
        alpha_show = AnimationUtils.loadAnimation(this, R.anim.alpha_show);

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

        edit_mode_menu = findViewById(R.id.edit_mode_menu);
        edit_mode_menu.setOnNavigationItemSelectedListener(edit_menu_listener);

        edit_mode_menu.setVisibility(View.INVISIBLE);
        edit_mode_menu.setEnabled(false);

        TextView Header = findViewById(R.id.day_of_the_week);
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        String dayName = dow.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH);
        switch (dayName) {
            case "Mon":
                Header.setText("Понедельник");
                Current_Day_Num = 1;
                break;
            case "Tue":
                Header.setText("Вторник");
                Current_Day_Num = 2;
                break;
            case "Wed":
                Header.setText("Среда");
                Current_Day_Num = 3;
                break;
            case "Thu":
                Header.setText("Четверг");
                Current_Day_Num = 4;
                break;
            case "Fri":
                Header.setText("Пятница");
                Current_Day_Num = 5;
                break;
            case "Sat":
                Header.setText("Суббота");
                Current_Day_Num = 6;
                break;
            case "Sun":
                Header.setText("Воскресенье");
                Current_Day_Num = 7;
                break;
        }

        Week_Mode_Calculate();

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
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Main_ListView.setSelection(position);
                Main_ListView.setSelected(true);

                ScheduleItem Item = Main_Array_List.get(position);
                current_row_id = Item.getObject_Id();

                show_edit_menu();

                patch.setVisibility(View.VISIBLE);
                curent_patch = true;
                return true;
            }
        });

        Main_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Хуяк", Toast.LENGTH_LONG).show();
            }
        });

        //
        MessageBox = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme_Dark);
        MessageBox.setMessage("Вы действительно хотите удалить этот элемент?");
        MessageBox.setIcon(R.drawable.ic_delete_black_24dp);
        //MessageBox

        MessageBox.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                delete_row();
            }
        });
        MessageBox.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        MessageBox.setCancelable(true);
    }

    private void delete_row() {
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);
        switch (Week) {  // В зависимости от текущей недели и дня недели выбирается нужная таблица из базы данных save_n.db
            case 1:
                switch (Current_Day_Num) {
                    case 1:
                        db.delete("Monday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 2:
                        db.delete("Tuesday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 3:
                        db.delete("Wednesday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 4:
                        db.delete("Thursday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 5:
                        db.delete("Friday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 6:
                        db.delete("Saturday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 7:
                        db.delete("Sunday_1", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                }
                break;
            case 2:
                switch (Current_Day_Num) {
                    case 1:
                        db.delete("Monday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 2:
                        db.delete("Tuesday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 3:
                        db.delete("Wednesday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 4:
                        db.delete("Thursday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 5:
                        db.delete("Friday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 6:
                        db.delete("Saturday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                    case 7:
                        db.delete("Sunday_2", "Object_Id = ?", new String[]{current_row_id});
                        db.close();
                        break;
                }
                break;
        }
        Toast.makeText(MainActivity.this, "Предмет удален", Toast.LENGTH_LONG).show();
        update_Main_ListView(Week, Current_Day_Num);

        hide_edit_menu();

        patch.setVisibility(View.INVISIBLE);
        curent_patch = false;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Week_Mode_Calculate() {
        int Year1 = settings.getInt("Year", 2019);
        int Month1 = settings.getInt("Month", 8);
        int Day1 = settings.getInt("Day", 1);

        Calendar date1 = new GregorianCalendar(Year1, Month1, Day1);

        Calendar date2 = Calendar.getInstance();
        int week = date2.get(Calendar.WEEK_OF_YEAR) - date1.get(Calendar.WEEK_OF_YEAR);


        if (week % 2 == 0) {
            Week = 2;
        } else {
            Week = 1;
        }

        Week_Btn = findViewById(R.id.Week_Stage);
        if (Week == 1) {
            Week = 2;
            Week_Btn.setText(R.string.Week_2);
        } else {
            Week = 1;
            Week_Btn.setText(R.string.Week_1);
        }
    }

    @Override
    public void onBackPressed() {
        if (edit_mode_menu.isEnabled()) {
            patch.setVisibility(View.INVISIBLE);

            curent_patch = true;
            hide_edit_menu();
            curent_patch = false;

            update_Main_ListView(Week, Current_Day_Num);
        } else {
            finish();
        }
    }

    public void Change_Week_Stage(View view) {
        Week_Btn = findViewById(R.id.Week_Stage);
        if (Week == 1) {
            Week = 2;
            Week_Btn.setText(R.string.Week_2);
        } else {
            Week = 1;
            Week_Btn.setText(R.string.Week_1);
        }
        update_Main_ListView(Week, Current_Day_Num);

        hide_edit_menu();
    }

    public void update_Main_ListView(Integer Week, Integer Day) {
        Main_ListView = findViewById(R.id.List_front);
        Main_Array_List = new ArrayList<ScheduleItem>();
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

        Cursor cursor = null;

        switch (Week) {  // В зависимости от текущей недели и дня недели выбирается нужная таблица из базы данных save_n.db
            case 1:
                switch (Day) {
                    case 1:
                        cursor = db.rawQuery("SELECT * FROM Monday_1;", null);
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
                switch (Day) {
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

        if (cursor.moveToFirst()) {
            do {
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

                String Context_Table = cursor.getString(11);

                String Item_Notes = cursor.getString(12);

                Main_Array_List.add(new ScheduleItem(Object_Id, Time0, Time1, Item_name, Teacher_name, Item_mode, Item_auditorium, Item_building, Teacher_Phone, Teacher_Mail, Favourite, Context_Table, Item_Notes));
            }
            while (cursor.moveToNext());
        }

        Main_Array_List_Adapter = new ScheduleListAdapter_flat(MainActivity.this, R.layout.list_item_layout_v3_flat, Main_Array_List);
        Main_ListView.setAdapter(Main_Array_List_Adapter);
        check_day_for_empty(cursor.getCount());
        cursor.close();
        db.close();
    }

    private void check_day_for_empty(int cursor_count) {
        TextView If_Day_Is_Empty = findViewById(R.id.Empty_Day_Text);
        if (cursor_count > 0) {
            If_Day_Is_Empty.setAlpha(0.0f);
        } else {
            If_Day_Is_Empty.setAlpha(1.0f);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener edit_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.edit_button:
                    Intent intent = new Intent(MainActivity.this, EditItemActivity.class);

                    intent.putExtra("Day", Current_Day_Num);
                    intent.putExtra("Week", Week);
                    intent.putExtra("Row_ID", current_row_id);

                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    break;
                case R.id.delete_button:
                    MessageBox.show();
            }

            return true;
        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener bot_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.add_button: // Добавление строки в список предметов

                    Intent intent = new Intent(MainActivity.this, AddItemActivity.class);

                    intent.putExtra("Day", Current_Day_Num);
                    intent.putExtra("Week", Week);

                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    break;
                case R.id.settings_button:
                    Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);

                    intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent2);
                    break;
                case R.id.menu_button:
                    TextView Header = findViewById(R.id.day_of_the_week);
                    LocalDate date = LocalDate.now();
                    DayOfWeek dow = date.getDayOfWeek();
                    String dayName = dow.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH);
                    switch (dayName) {
                        case "Mon":
                            Header.setText("Понедельник");
                            Current_Day_Num = 1;
                            break;
                        case "Tue":
                            Header.setText("Вторник");
                            Current_Day_Num = 2;
                            break;
                        case "Wed":
                            Header.setText("Среда");
                            Current_Day_Num = 3;
                            break;
                        case "Thu":
                            Header.setText("Четверг");
                            Current_Day_Num = 4;
                            break;
                        case "Fri":
                            Header.setText("Пятница");
                            Current_Day_Num = 5;
                            break;
                        case "Sat":
                            Header.setText("Суббота");
                            Current_Day_Num = 6;
                            break;
                        case "Sun":
                            Header.setText("Воскресенье");
                            Current_Day_Num = 7;
                            break;
                    }
                    break;
            }
            Week_Mode_Calculate();
            update_Main_ListView(Week, Current_Day_Num);

            hide_edit_menu();

            patch.setVisibility(View.INVISIBLE);
            curent_patch = false;
            return true;
        }
    };

    public void Day_Change_Right() {
        TextView Header = findViewById(R.id.day_of_the_week);

        hide_edit_menu();

        patch.setVisibility(View.INVISIBLE);
        curent_patch = false;
        switch (Header.getText().toString()) {
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
        update_Main_ListView(Week, Current_Day_Num);
    }

    public void Day_Change_Left() {
        TextView Header = findViewById(R.id.day_of_the_week);

        hide_edit_menu();

        patch.setVisibility(View.INVISIBLE);
        curent_patch = false;
        switch (Header.getText().toString()) {
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
        update_Main_ListView(Week, Current_Day_Num);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener top_menu_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            TextView Header = findViewById(R.id.day_of_the_week);

            switch (menuItem.getItemId()) {
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
