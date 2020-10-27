package com.example.groupproject;

import java.util.ArrayList;

public class CalendarModel {
    public static ArrayList<Calendar> calendarList;

    public String getPosition(int position) {
        return CalendarModel.calendarList.get(position).calendar;
    }

    public static class Calendar{
        public String calendar;

        public Calendar(String calendar) {
            this.calendar = calendar;
        }
    }

    private CalendarModel(){
        calendarList = new ArrayList<Calendar>();
    }

    //loads objects into array
    private void loadItems(){
        calendarList.add(new Calendar("Course"));

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
