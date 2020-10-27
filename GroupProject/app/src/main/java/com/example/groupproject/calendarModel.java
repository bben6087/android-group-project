package com.example.groupproject;

import java.util.ArrayList;

import static com.example.groupproject.calendarModel.calendarList;

public class calendarModel {
    public static ArrayList<Calendar> calendarList;

    public String getPosition(int position) {
        return calendarModel.calendarList.get(position).calendar;
    }

    public static class Calendar{
        public String calendar;

        public Calendar(String calendar) {
            this.calendar = calendar;
        }
    }

    private calendarModel(){
        calendarList = new ArrayList<Calendar>();
    }

    //loads objects into array
    private void loadItems(){
        calendarList.add(new Calendar("Course"));

    }
    // Creates the model
    private static calendarModel theModel = null;
    public static calendarModel getSingleton(){
        if(theModel == null){
            theModel = new calendarModel();
        }
        return theModel;
    }
    // adds price when clicked on recycler view
    public static double addItem(int position){
        return position;
    }
}
