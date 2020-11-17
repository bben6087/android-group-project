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

    public String message;
    private RecyclerView messageRV = null;
    private GestureDetectorCompat detector = null;
    private MessengerModel myModel = null;
    private MessengerAdapter myAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_page);

    myModel= MessengerModel.getSingleton();
    myAdapter = new MessengerAdapter();

    messageRV = findViewById(R.id.messageRV);
    messageRV.setAdapter(myAdapter);

    LinearLayoutManager lin = new LinearLayoutManager(this);
        messageRV.setLayoutManager(lin);
    Button sendBTN = findViewById(R.id.sendBTN);
    Button messageBTN = findViewById(R.id.messageBTN);
        sendBTN.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText messageET = findViewById(R.id.messageET);
            String messageStr= messageET.getText().toString();
            if(messageStr.equals("")){
                Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
            }
            else{
                myModel.messages.add(
                        new MessengerModel.Message(messageStr));
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                messageET.setText("");
                ParseObject message = new ParseObject("messageString");
                message.put(messageStr);

            }


        }
    });
        messageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
}
}
