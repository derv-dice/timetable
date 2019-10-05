package com.example.mark_5;

public class Schedule_Item {
    private String time0;                   // Время начала пары
    private String time1;                   // Время окончания пары
    private String Item_Name;               // Название предмета
    private String Teacher_Name;            // Имя преподавателя
    private String Item_Mode;               // Лекция, Практика и т.д.
    private String Item_Auditorium;         // Аудитория
    private String Item_Building;           // Корпус

    Schedule_Item(String _time0, String _time1, String _Item_Name, String _Teacher_name, String _Item_Mode, String _Item_Auditorium, String _Item_Building){
        time0 = _time0;
        time1 = _time1;
        Item_Name = _Item_Name;
        Teacher_Name = _Teacher_name;
        Item_Mode = _Item_Mode;
        Item_Auditorium = _Item_Auditorium;
        Item_Building = _Item_Building;
    }


    // Дальше идут методы get()


    public String getItem_Name() {
        return Item_Name;
    }

    public String getTime0() {
        return time0;
    }

    public String getItem_Mode() {
        return Item_Mode;
    }

    public String getTime1() {
        return time1;
    }

    public String getTeacher_Name() {
        return Teacher_Name;
    }

    public String getItem_Auditorium() {
        return Item_Auditorium;
    }

    public String getItem_Building() {
        return Item_Building;
    }

}
