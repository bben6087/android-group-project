package com.example.groupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MessengerAdapter extends RecyclerView.Adapter <MessengerAdapter.MessageViewHolder> {

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public MessageViewHolder(View v) {
            super(v);
        }}

    private MessengerModel theModel;

    public MessengerAdapter() {
        theModel = MessengerModel.getSingleton();
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a TextHolder
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list, parent, false);
        MessageViewHolder vh = new MessageViewHolder(v);
        return vh;

    }
    //Puts the Array on the recycler view
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        TextView messageTV = holder.itemView.findViewById(R.id.courseNameTV);

        messageTV.setText(theModel.messages.get(position).messages);
    }


    //Gets Array size
    @Override
    public  int getItemCount() {
        return theModel.messages.size();
    }
}


