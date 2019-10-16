package com.example.mark_5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditItemActivity extends AppCompatActivity {

    public TextView t1, t2, t3, t4,t5, t6, t7, t8, t9;
    public String t1_str, t2_str, t3_str, t4_str, t5_str, t6_str, t7_str, t8_str, t9_str;
    private int Current_Day_Num = 0;
    private int Current_Week = 0;
    private String Current_Row_ID = "0";
    private String items_DB = "save11.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Bundle Data_From_Last_Activity = getIntent().getExtras();;
        Current_Day_Num = Data_From_Last_Activity.getInt("Day");
        Current_Week = Data_From_Last_Activity.getInt("Week");
        Current_Row_ID = Data_From_Last_Activity.getString("Row_ID");

        t1 = findViewById(R.id.editText4);
        t2 = findViewById(R.id.editText5);
        t3 = findViewById(R.id.editText6);
        t4 = findViewById(R.id.editText7);
        t5 = findViewById(R.id.editText8);
        t6 = findViewById(R.id.editText9);
        t7 = findViewById(R.id.editText10);
        t8 = findViewById(R.id.editText12);
        t9 = findViewById(R.id.editText11);

        Cursor cursor = null;
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

        switch (Current_Week){  // В зависимости от текущей недели и дня недели выбирается нужная таблица из базы данных save_n.db
            case 1:
                switch (Current_Day_Num){
                    case 1:
                        cursor = db.rawQuery("SELECT * FROM Monday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 2:
                        cursor = db.rawQuery("SELECT * FROM Tuesday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 3:
                        cursor = db.rawQuery("SELECT * FROM Wednesday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 4:
                        cursor = db.rawQuery("SELECT * FROM Thursday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 5:
                        cursor = db.rawQuery("SELECT * FROM Friday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 6:
                        cursor = db.rawQuery("SELECT * FROM Saturday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 7:
                        cursor = db.rawQuery("SELECT * FROM Sunday_1 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                }
                break;
            case 2:
                switch (Current_Day_Num){
                    case 1:
                        cursor = db.rawQuery("SELECT * FROM Monday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 2:
                        cursor = db.rawQuery("SELECT * FROM Tuesday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 3:
                        cursor = db.rawQuery("SELECT * FROM Wednesday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 4:
                        cursor = db.rawQuery("SELECT * FROM Thursday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 5:
                        cursor = db.rawQuery("SELECT * FROM Friday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 6:
                        cursor = db.rawQuery("SELECT * FROM Saturday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                    case 7:
                        cursor = db.rawQuery("SELECT * FROM Sunday_2 WHERE Object_Id = " + Current_Row_ID + " ;",null);
                        break;
                }
                break;
        }

        if(cursor.moveToFirst()){
            do{
                if (cursor.getString(1)!=null){t1.setText(cursor.getString(1));}
                if (cursor.getString(2)!=null){t2.setText(cursor.getString(2));}
                if (cursor.getString(3)!=null){t3.setText(cursor.getString(3));}
                if (cursor.getString(4)!=null){t4.setText(cursor.getString(4));}
                if (cursor.getString(5)!=null){t5.setText(cursor.getString(5));}
                if (cursor.getString(6)!=null){t6.setText(cursor.getString(6));}
                if (cursor.getString(7)!=null){t7.setText(cursor.getString(7));}
                if (cursor.getString(8)!=null){t8.setText(cursor.getString(8));}
                if (cursor.getString(9)!=null){t9.setText(cursor.getString(9));}
            }
            while(cursor.moveToNext());
        }

        db.close();
        cursor.close();

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
                    Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
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

            SQLiteDatabase db = getApplicationContext().openOrCreateDatabase(items_DB, MODE_PRIVATE, null);

            switch (Current_Week){
                case 1:   // ЧИСЛИТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            db.beginTransaction();
                            db.update("Monday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 2:
                            db.beginTransaction();
                            db.update("Tuesday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 3:
                            db.beginTransaction();
                            db.update("Wednesday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 4:
                            db.beginTransaction();
                            db.update("Thursday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 5:
                            db.beginTransaction();
                            db.update("Friday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 6:
                            db.beginTransaction();
                            db.update("Saturday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 7:
                            db.beginTransaction();
                            db.update("Sunday_1", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                    }
                    break;
                case 2:   // ЗНАМЕНАТЕЛЬ
                    switch (Current_Day_Num){
                        case 1:
                            db.beginTransaction();
                            db.update("Monday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 2:
                            db.beginTransaction();
                            db.update("Tuesday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 3:
                            db.beginTransaction();
                            db.update("Wednesday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 4:
                            db.beginTransaction();
                            db.update("Thursday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 5:
                            db.beginTransaction();
                            db.update("Friday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 6:
                            db.beginTransaction();
                            db.update("Saturday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                        case 7:
                            db.beginTransaction();
                            db.update("Sunday_2", Val, "Object_Id = " + Current_Row_ID, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                    }
                    break;
            }
            db.close();

            Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Предмет изменен", Toast.LENGTH_LONG).show();
            return true;
        }
    };

}
