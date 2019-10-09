package com.example.mark_5;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ScheduleListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<ScheduleItem> objects;
    private String items_DB = "save8.db";

    //ScheduleListAdapter(Context context, int resource, ArrayList<ScheduleItem> items) {
    ScheduleListAdapter(Context context, int resource, ArrayList<ScheduleItem> items) {
        this.layout = resource;
        this.objects = items;
        this.inflater = LayoutInflater.from(context);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ScheduleItem Item = objects.get(position);

        viewHolder.Time0.setText(Item.getTime0());
        viewHolder.Time1.setText(Item.getTime1());
        viewHolder.Item_Name.setText(Item.getItem_Name());
        viewHolder.Teacher_Name.setText(Item.getTeacher_Name());
        viewHolder.Item_Mode.setText(Item.getItem_Mode());
        viewHolder.Item_Auditorium.setText(Item.getItem_Auditorium());
        viewHolder.Item_Building.setText(Item.getItem_Building());

        final View finalView = convertView;

        if ((Item.getTeacher_Mail().equals("")) && (Item.getTeacher_Phone().equals(""))){
            viewHolder.File_Button.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.File_Button.setVisibility(View.VISIBLE);
        }

        if (Item.getFavourite().equals("1")){
            viewHolder.Mark_Button.setBackground(ContextCompat.getDrawable(finalView.getContext(), R.drawable.ic_mainlistrow_v2_checked_mark_btn));
        }
        else {
            viewHolder.Mark_Button.setBackground(ContextCompat.getDrawable(finalView.getContext(), R.drawable.ic_mainlistrow_v2_mark_btn));
        }

        viewHolder.File_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(finalView.getContext(), "Номер телефона: " + Item.getTeacher_Phone() + '\n' +"Почта: " + Item.getTeacher_Mail(), Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.Mark_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Запрос в БД на добавление в избранное

                // Временная визуальная хрень
                viewHolder.Mark_Button.setBackground(ContextCompat.getDrawable(finalView.getContext(), R.drawable.ic_mainlistrow_v2_checked_mark_btn));
                //В ременная визуальная хрень
            }
        });

        viewHolder.List_Item_Layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


        return convertView;
    }

    //  https://metanit.com/java/android/5.8.php

    private class ViewHolder {
        final TextView Time0, Time1, Item_Name, Teacher_Name, Item_Mode, Item_Auditorium, Item_Building;
        final Button File_Button, Mark_Button;
        final ConstraintLayout List_Item_Layout;
        ViewHolder(View view){
            Time0 = view.findViewById(R.id.textView_time0);
            Time1 = view.findViewById(R.id.textView_time1);
            Item_Name = view.findViewById(R.id.textView_item_name);
            Teacher_Name = view.findViewById(R.id.textView_item_teacher);
            Item_Mode = view.findViewById(R.id.textView_item_mode);
            Item_Auditorium = view.findViewById(R.id.textView_item_auditorium);
            Item_Building = view.findViewById(R.id.textView_item_building);
            File_Button = view.findViewById(R.id.file_button);
            Mark_Button = view.findViewById(R.id.mark_button);
            List_Item_Layout = view.findViewById(R.id.Item_List_Layout);
        }
    }

}


