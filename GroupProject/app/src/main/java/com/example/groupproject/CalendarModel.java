package com.example.groupproject;

import java.util.ArrayList;

public class CalendarModel {
    public static ArrayList<Calendar> calendarList;

    public String getPosition(int position) {
        return CalendarModel.calendarList.get(position).course;
    }

    public String getTime(int position) {
        return CalendarModel.calendarList.get(position).time;
    }

    public String getDate(int position){
        return CalendarModel.calendarList.get(position).date;
    }


    public static class Calendar{
        public String date;
        public String time;
        public String course;

        public Calendar(String course, String date, String time) {
            this.date = date;
            this.time = time;
            this.course = course;
        }
    }

    private CalendarModel(){
        calendarList = new ArrayList<Calendar>();
    }

    //loads objects into array
    private void loadItems(){
        calendarList.add(new Calendar("course", "date", "12"));

    }
    // Creates the model
    private static CalendarModel theModel = null;
    public static CalendarModel getSingleton(){
        if(theModel == null){
            theModel = new CalendarModel();
        }
        return theModel;
    }
    // adds price when clicked on recycler view
    public static double addItem(int position){
        return position;
    }
}
