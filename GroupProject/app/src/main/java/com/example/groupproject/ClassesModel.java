package com.example.groupproject;

import java.util.ArrayList;

public class ClassesModel {
    public String getPosition(int position) {
        return ClassesModel.courseList.get(position).course;
    }

    public static class Classes{
        public String course;

        public Classes(String course) {
            this.course = course;
        }
    }
    public static ArrayList<Classes> courseList;

    private ClassesModel(){
        courseList = new ArrayList<Classes>();
    }
    //loads objects into array
    private void loadItems(){
        courseList.add(new Classes("Course"));

    }
    // Creates the model
    private static ClassesModel theModel = null;
    public static ClassesModel getSingleton(){
        if(theModel == null){
            theModel = new ClassesModel();
        }
        return theModel;
    }
    // adds price when clicked on recycler view
    public static double addItem(int position){
        return position;
    }
}
