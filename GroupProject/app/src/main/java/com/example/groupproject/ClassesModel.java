package com.example.groupproject;

import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ClassesModel {
    public static ArrayList<Classes> courseList;

    public String getPosition(int position) {
        return ClassesModel.courseList.get(position).course;
    }

    public static class Classes{
        public String course;

        public Classes(String course) {
            this.course = course;
        }
    }

    private ClassesModel() {
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
