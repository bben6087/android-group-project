package com.example.groupproject;

import java.util.ArrayList;

public class CalendarModel {
    public static ArrayList<Calendar> calendarList;

    public String getPosition(int position) {
        return CalendarModel.calendarList.get(position).calendar;
    }

    public static class Calendar{
        public String calendar;
        public String time;
        public String name;

        public Calendar(String name, String calendar, String time) {
            this.calendar = calendar;
            this.time = time;
            this.name = name;
        }
    }

    private CalendarModel(){
        calendarList = new ArrayList<Calendar>();
    }

    //loads objects into array
    private void loadItems(){
        calendarList.add(new Calendar("Steve", "Course", "12 Monday"));

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
