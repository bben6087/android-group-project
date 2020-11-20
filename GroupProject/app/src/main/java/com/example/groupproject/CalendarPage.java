package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = calendarRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = calendarRV.getChildViewHolder(view);
                if (holder instanceof CalendarAdapter.CalendarViewHolder) {
                    int position = holder.getAdapterPosition();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Calendar");
                    query.whereMatches("username", ClassesPage.getSnum());
                    query.whereMatches("course", myModel.getPosition(position));
                    query.whereMatches("date", myModel.getDate(position));
                    query.whereMatches("time",myModel.getTime(position));
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
                                    try {
                                        user.delete();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }

                            }
                        }
                    });
                    // handle single tap
                    myModel.calendarList.remove(position);
                    myAdapter.notifyItemRemoved(position);

                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }

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
        detector = new GestureDetectorCompat(this,
                new CalendarPage.RecyclerViewOnGestureListener());
        // add the listener to the recycler
        calendarRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });

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
                                    (schedule.getString("course"),schedule.getString("date"),schedule.getString("time")));
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
                EditText courseET = findViewById(R.id.courseNameeeET);
                String courseStr = courseET.getText().toString();
                EditText dateET = findViewById(R.id.dateET);
                String dateStr = dateET.getText().toString();
                EditText timeET = findViewById(R.id.timeET);
                String timeStr= timeET.getText().toString();
                if(courseStr.equals("") || dateStr.equals("") || timeStr.equals("")){
                    Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
                }
                else{
                    ParseObject classes = new ParseObject("Calendar");
                    classes.put("course", courseStr);
                    classes.put("date", dateStr);
                    classes.put("time", timeStr );
                    classes.put("username", ClassesPage.getSnum());
                    classes.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("Parse", "Reg: " + e);
                        }
                    });
                    myModel.calendarList.add(
                            new CalendarModel.Calendar(courseStr, dateStr, timeStr));
                    myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                    courseET.setText("");
                    dateET.setText("");
                    timeET.setText("");
                }

            }
        });
    }
}