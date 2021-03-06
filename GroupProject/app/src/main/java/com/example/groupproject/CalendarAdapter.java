package com.example.groupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        public CalendarViewHolder(View v) {
            super(v);
        }
    }

    private CalendarModel theModel;

    public CalendarAdapter() {
        theModel = CalendarModel.getSingleton();
    }
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a TextHolder
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_list, parent, false);
        CalendarViewHolder vh = new CalendarViewHolder(v);
        return vh;

    }
    //Puts the Array on the recycler view
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        TextView courseTV = holder.itemView.findViewById(R.id.courseTV);
        courseTV.setText(theModel.calendarList.get(position).course);
        TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
        dateTV.setText(theModel.calendarList.get(position).date);
        TextView timeTV = holder.itemView.findViewById(R.id.timeTV);
        timeTV.setText(theModel.calendarList.get(position).time);


    }
    //Gets Array size
    @Override
    public  int getItemCount() {
        return theModel.calendarList.size();
    }
}
