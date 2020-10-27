package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalendarPage extends AppCompatActivity {

    public String course;
    private RecyclerView calendarRV = null;
    private GestureDetectorCompat detector = null;
    private CalendarModel myModel = null;
    private CalendarAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);
        myModel= CalendarModel.getSingleton();

        myAdapter = new CalendarAdapter();

        calendarRV = findViewById(R.id.calendarRV);
        calendarRV.setAdapter(myAdapter);

        LinearLayoutManager lin = new LinearLayoutManager(this);
        calendarRV.setLayoutManager(lin);
        Button addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText studentET = findViewById(R.id.studentET);
                String studentStr = studentET.getText().toString();
                myModel.calendarList.add(
                        new CalendarModel.Calendar(studentStr));
                //Add more edit texts to update adapter and recycler view
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                studentET.setText("");
            }
        });
    }
}