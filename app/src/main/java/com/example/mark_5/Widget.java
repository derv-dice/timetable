package com.example.mark_5;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;

public class Widget extends AppWidgetProvider {

    private int Current_Day_Num = 1;
    private int Week = 1;
    private String items_DB = "save11.db";
    private ArrayList<ScheduleItem> Main_Array_List;
    private ScheduleListAdapter_flat Main_Array_List_Adapter;
    private TextView Header;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
        ComponentName componentName = new ComponentName(context, Widget.class);

        remoteViews.setTextViewText(R.id.header_widget, "It's works!");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}

/*  Прописать в манифесте для работы виджета (но он еще не сделан)

 <receiver
            android:name="Widget"
            android:icon="@drawable/ic_app_icon"
            android:label="Виджет">
            <intent-filter>
                <action
                    android:name="android.appwidget.action.APPWIDGET_UPDATE">
                </action>
            </intent-filter>
            <meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/widget_metadata">
            </meta-data>
        </receiver>


 */