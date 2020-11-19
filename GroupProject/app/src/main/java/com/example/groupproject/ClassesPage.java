package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClassesPage extends AppCompatActivity {
    public static String KEY_COURSE = "KEY_COURSE";
    private static String snum;
    public String course;
    private RecyclerView classRV = null;
    private GestureDetectorCompat detector = null;
    private ClassesModel myModel = null;
    private ClassesAdapter myAdapter = null;
    final List<ParseObject> lastSearch = new ArrayList<ParseObject>();

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = classRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = classRV.getChildViewHolder(view);
                if (holder instanceof ClassesAdapter.ClassesViewHolder) {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    course = myModel.getPosition(position);
                    goCourse(view);
                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }

    public void goCourse(View v) {
        Intent ini = new Intent(this, CoursePage.class);
        ini.putExtra(KEY_COURSE, course);
        startActivity(ini);
    }

    public static String getSnum(){
        return snum;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_page);
        snum = getIntent().getStringExtra(MainActivity.KEY_SNUM);
        TextView loginTitleTV = findViewById(R.id.loginTitleTV);
        loginTitleTV.setText("Logged in as " + snum);


        myModel= ClassesModel.getSingleton();
        myAdapter = new ClassesAdapter();

        classRV = findViewById(R.id.calendarRV);
        classRV.setAdapter(myAdapter);

        LinearLayoutManager lin = new LinearLayoutManager(this);
        classRV.setLayoutManager(lin);
        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        classRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });
        myModel.courseList.clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Classes");
        query.whereMatches("username", snum);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                lastSearch.clear();
                lastSearch.addAll(objects);
                String toShow = "";
                if (e == null) {
                    //success
                    for (int i = 0; i < objects.size(); i++) {
                        ParseObject user = objects.get(i);
                        if (user.get("username").equals(snum)) {
                            myModel.courseList.add(new ClassesModel.Classes(user.getString("classname")));
                            myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                        }
                    }

                }
            }
        });

        Button addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText classET = findViewById(R.id.classET);
                String classStr = classET.getText().toString();
                if(classStr.equals("")){
                    Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
                }
                else{
                    ParseObject classes = new ParseObject("Classes");
                    classes.put("classname", classStr);
                    classes.put("username", snum);
                    classes.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("Parse", "Reg: " + e);
                        }
                    });
                    myModel.courseList.add(
                            new ClassesModel.Classes(classStr));
                    myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                    classET.setText("");
                }
            }
        });
    }

    public void goCally(View v){
        Intent calender = new Intent(this, CalendarPage.class);
        startActivity(calender);
    }

    public static String getSnum(View v){
        return snum;
    }

    }