package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends AppCompatActivity {

    public String course;
    private RecyclerView calendarRV = null;
    private GestureDetectorCompat detector = null;
    private CalendarModel myModel = null;
    private CalendarAdapter myAdapter = null;
    final List<ParseObject> lastSearch = new ArrayList<ParseObject>();

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

        myModel.calendarList.clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Calendar");
        query.whereMatches("username", ClassesPage.getSnum());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                lastSearch.clear();
                lastSearch.addAll(objects);
                String toShow = "";
                if (e == null) {
                    //success
                    for (int i = 0; i < objects.size(); i++) {
                        ParseObject schedule = objects.get(i);
                        if (schedule.get("username").equals(ClassesPage.getSnum())) {
                            myModel.calendarList.add(new CalendarModel.Calendar
                                    (schedule.getString("name"),schedule.getString("course"),schedule.getString("time")));
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
                EditText studentET = findViewById(R.id.studentET);
                String studentStr = studentET.getText().toString();
                EditText courseET = findViewById(R.id.courseNameET);
                String courseStr = courseET.getText().toString();
                EditText timeET = findViewById(R.id.timeET);
                String timeStr= timeET.getText().toString();
                if(studentStr.equals("") || courseStr.equals("") || timeStr.equals("")){
                    Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
                }
                else{
                    ParseObject classes = new ParseObject("Calendar");
                    classes.put("name", studentStr);
                    classes.put("course", courseStr);
                    classes.put("time", timeStr );
                    classes.put("username", ClassesPage.getSnum());
                    classes.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("Parse", "Reg: " + e);
                        }
                    });
                    myModel.calendarList.add(
                            new CalendarModel.Calendar(studentStr, courseStr, timeStr));
                    myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                    studentET.setText("");
                    courseET.setText("");
                    timeET.setText("");
                }

            }
        });
    }
}