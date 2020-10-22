package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
}