package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import android.widget.Toast;

import java.text.DecimalFormat;




public class MessengerPage extends AppCompatActivity {

    public String course;
    private RecyclerView calendarRV = null;
    private GestureDetectorCompat detector = null;
    private CalendarModel myModel = null;
    private CalendarAdapter myAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_page);
    }

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
            EditText calendarET = findViewById(R.id.calendarET);
            String calendarStr = calendarET.getText().toString();
            EditText timeET = findViewById(R.id.timeET);
            String timeStr= timeET.getText().toString();
            if(studentStr.equals("") || calendarStr.equals("") || timeStr.equals("")){
                Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
            }
            else{
                myModel.calendarList.add(
                        new CalendarModel.Calendar(studentStr, calendarStr, timeStr));
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                studentET.setText("");
                calendarET.setText("");
                timeET.setText("");
            }

        }
    });
}
}
}