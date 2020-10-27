package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CoursePage extends AppCompatActivity {
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        courseName = getIntent().getStringExtra(ClassesPage.KEY_COURSE);
        TextView courseTV = findViewById(R.id.courseTV);
        courseTV.setText(courseName);
    }

    public void goCally(View v){
        Intent calenderGo = new Intent(this, CalendarPage.class);
        startActivity(calenderGo);
    }

    public void goMessenger(View v){
        Intent messengerGo = new Intent(this, MessengerPage.class);
        startActivity(messengerGo);
    }

    public void goScheduler(View v){
        Intent schedulerGo = new Intent(this, SchedulePage.class);
        startActivity(schedulerGo);
    }
}