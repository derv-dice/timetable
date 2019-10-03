package com.example.mark_5;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_layout_v2, parent, false);
        }

        Button file = view.findViewById(R.id.file_button);
        file.setVisibility(View.INVISIBLE);

        final Button mark = view.findViewById(R.id.mark_button);

        Schedule_Item schedule_item = getItem_List(position);


        TextView text_view_time0 = view.findViewById(R.id.textView_time0);
        text_view_time0.setText(schedule_item.getTime0());

        TextView text_view_time1 = view.findViewById(R.id.textView_time1);
        text_view_time1.setText(schedule_item.getTime1());

        TextView textView_item_teacher = view.findViewById(R.id.textView_item_teacher);
        textView_item_teacher.setText(schedule_item.getTeacher_Name());

        TextView text_view_item_mode = view.findViewById(R.id.textView_item_mode);
        text_view_item_mode.setText(schedule_item.getItem_Mode());

        TextView text_view_item_auditorium = view.findViewById(R.id.textView_item_auditorium);
        text_view_item_auditorium.setText("ауд. " + schedule_item.getItem_Auditorium());

        TextView text_view_item_building = view.findViewById(R.id.textView_item_building);
        text_view_item_building.setText("к. " + schedule_item.getItem_Building());

        TextView text_view_item_name = view.findViewById(R.id.textView_item_name);
        text_view_item_name.setText(schedule_item.getItem_Name());

        ConstraintLayout Item_List_Field = view.findViewById(R.id.Item_List_Layout);




        final View finalView = view;
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(finalView.getContext(),"e-mail: "+"\n"+"Номер: " , Toast.LENGTH_LONG).show();
            }
        });

        mark.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                mark.setBackground(ContextCompat.getDrawable(ctx,R.drawable.ic_mainlistrow_v2_checked_mark_btn));
            }
        });


        return view;
    }

    // товар по позиции
    Schedule_Item getItem_List(int position) {
        return ((Schedule_Item) getItem(position));
    }
}
