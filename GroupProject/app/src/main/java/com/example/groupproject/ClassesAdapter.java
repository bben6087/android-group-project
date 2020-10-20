package com.example.groupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder> {

        public static class ClassesViewHolder extends RecyclerView.ViewHolder {
            public ClassesViewHolder(View v) {
                super(v);
            }
        }

        private ClassesModel theModel;

    public ClassesAdapter() {
            theModel = ClassesModel.getSingleton();
        }
        @NonNull
        @Override
        public ClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            // Create a TextHolder
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list, parent, false);
            ClassesViewHolder vh = new ClassesViewHolder(v);
            return vh;

        }
        //Puts the Array on the recycler view
        @Override
        public void onBindViewHolder(@NonNull ClassesViewHolder holder, int position) {
            TextView courseTV = holder.itemView.findViewById(R.id.courseNameTV);

            courseTV.setText(theModel.courseList.get(position).course);
        }
        //Gets Array size
        @Override
        public  int getItemCount() {
            return theModel.courseList.size();
        }
}
