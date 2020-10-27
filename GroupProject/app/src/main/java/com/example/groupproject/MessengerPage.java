package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< Updated upstream:GroupProject/app/src/main/java/com/example/groupproject/MessengerPage.java

import android.os.Bundle;

public class MessengerPage extends AppCompatActivity {
=======
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
import android.widget.TextView;

import java.text.DecimalFormat;

public class calendarPage extends AppCompatActivity {
    public static String KEY_CALENDAR = "KEY_CALENDAR";
    public String calendar;
    private RecyclerView calendarRV = null;
    private GestureDetectorCompat detector = null;
    private ClassesModel myModel = null;
    private ClassesAdapter myAdapter = null;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = calendarRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = calendarRV.getChildViewHolder(view);
                if (holder instanceof ClassesAdapter.ClassesViewHolder) {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    calendar = myModel.getPosition(position);
                    goCalendar(view);
                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }

    public void goCalendar(View v) {
        Intent ini = new Intent(this, CoursePage.class);
        ini.putExtra(KEY_CALENDAR, calendar);
        startActivity(ini);
    }

>>>>>>> Stashed changes:GroupProject/app/src/main/java/com/example/groupproject/calendarPage.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream:GroupProject/app/src/main/java/com/example/groupproject/MessengerPage.java
        setContentView(R.layout.activity_messenger_page);
    }
=======
        setContentView(R.layout.calendar_page);

        myModel= calendarModel.getSingleton();

        myAdapter = new calendarAdapter();

        calendarRV = findViewById(R.id.calendarRV);
        calendarRV.setAdapter(myAdapter);

        LinearLayoutManager lin = new LinearLayoutManager(this);
        calendarRV.setLayoutManager(lin);
        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        calendarRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });
        Button addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText calendarET = findViewById(R.id.calendarET);
                String calendarStr = calendarET.getText().toString();
                myModel.calendarList.add(
                        new calendarModel.Calendar(calendarStr));
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                calendarET.setText("");
            }
        });
    }

    public void goCally(View v){
        Intent calender = new Intent();
        startActivity(calender);
    }

>>>>>>> Stashed changes:GroupProject/app/src/main/java/com/example/groupproject/calendarPage.java
}