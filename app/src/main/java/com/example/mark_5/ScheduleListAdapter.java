package com.example.mark_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<ScheduleItem> objects;

    ScheduleListAdapter(Context context, int resource, ArrayList<ScheduleItem> items) {
        //super(context, resource, items);
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
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ScheduleItem Item = objects.get(position);

        viewHolder.Time0.setText(Item.getTime0());
        viewHolder.Time1.setText(Item.getTime1());
        viewHolder.Item_Name.setText(Item.getItem_Name());
        viewHolder.Teacher_Name.setText(Item.getTeacher_Name());
        viewHolder.Item_Mode.setText(Item.getItem_Mode());
        viewHolder.Item_Auditorium.setText(Item.getItem_Auditorium());
        viewHolder.Item_Building.setText(Item.getItem_Building());

        return convertView;
    }

    //  https://metanit.com/java/android/5.8.php

    private class ViewHolder {
        final TextView Time0, Time1, Item_Name, Teacher_Name, Item_Mode, Item_Auditorium, Item_Building;
        ViewHolder(View view){
            Time0 = (TextView) view.findViewById(R.id.textView_time0);
            Time1 = (TextView) view.findViewById(R.id.textView_time1);
            Item_Name = (TextView) view.findViewById(R.id.textView_item_name);
            Teacher_Name = (TextView) view.findViewById(R.id.textView_item_teacher);
            Item_Mode = (TextView) view.findViewById(R.id.textView_item_mode);
            Item_Auditorium = (TextView) view.findViewById(R.id.textView_item_auditorium);
            Item_Building = (TextView) view.findViewById(R.id.textView_item_building);
        }
    }

}


