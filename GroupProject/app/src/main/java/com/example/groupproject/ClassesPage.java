package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClassesPage extends AppCompatActivity {
    public static String KEY_COURSE = "KEY_COURSE";
    public String course;
    private RecyclerView classRV = null;
    private GestureDetectorCompat detector = null;
    private ClassesModel myModel = null;
    private ClassesAdapter myAdapter = null;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_page);

        myModel= ClassesModel.getSingleton();

        myAdapter = new ClassesAdapter();

        classRV = findViewById(R.id.classRV);
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
        Button addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classET = findViewById(R.id.classET);
                String classStr = classET.getText().toString();
                myModel.courseList.add(
                        new ClassesModel.Classes(classStr));
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                classET.setText("");
            }
        });
    }

    public void goCally(View v){
        Intent calender = new Intent(this, CalendarPage.class);
        startActivity(calender);
    }

    }