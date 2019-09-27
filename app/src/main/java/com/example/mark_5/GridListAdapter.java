package com.example.mark_5;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GridListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Schedule_Item> objects;

    GridListAdapter(Context context, ArrayList<Schedule_Item> items) {
        ctx = context;
        objects = items;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_layout, parent, false);
        }

        Schedule_Item schedule_item = getItem_List(position);

        String time = schedule_item.getTime0() + "&#10;" + schedule_item.getTime1();

        ((Button) view.findViewById(R.id.btn_1)).setText(time);
        ((Button) view.findViewById(R.id.btn_2)).setText(schedule_item.getItem_Name());
        ((Button) view.findViewById(R.id.btn_3)).setText(schedule_item.getItem_Mode());
        ((Button) view.findViewById(R.id.btn_4)).setText(schedule_item.getItem_Auditorium());

        return view;
    }

    // товар по позиции
    Schedule_Item getItem_List(int position) {
        return ((Schedule_Item) getItem(position));
    }
}
