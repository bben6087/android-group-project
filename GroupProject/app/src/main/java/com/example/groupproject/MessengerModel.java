package com.example.groupproject;

import java.util.ArrayList;

public class MessengerModel {


    public static ArrayList<MessengerModel.Message> messages;

    public String getPosition(int position) {
        return MessengerModel.messages.get(position).messages;
    }

    public static class Message{
        public String messages;

        public Message(String messages) {
            this.messages = messages;
        }
    }

    private MessengerModel(){
        messages = new ArrayList<Message>();
    }

    //loads objects into array
    private void loadItems(){
        messages.add(new Message("sample"));

    }
    // Creates the model
    private static MessengerModel theModel = null;
    public static MessengerModel getSingleton(){
        if(theModel == null){
            theModel = new MessengerModel();
        }
        return theModel;
    }
    // adds price when clicked on recycler view
    public static double addItem(int position){
        return position;
    }
}


