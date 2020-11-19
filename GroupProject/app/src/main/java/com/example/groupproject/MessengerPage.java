package com.example.groupproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

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
            EditText nameET = findViewById(R.id.nameET);
            String nameStr= nameET.getText().toString();
            if(messageStr.equals("")){
                Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
            }
            else{
                myModel.messages.add(
                        new MessengerModel.Message(messageStr));
                myAdapter.notifyItemChanged(myAdapter.getItemCount()-1);
                messageET.setText("");
                ParseObject message = new ParseObject("messageString");
                ParseObject name = new ParseObject("nameMsg");
                message.put(messageStr, name);


            }


        }

    });
        messageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("MessageString");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, com.parse.ParseException e) {

                    }
                    public void done(List<ParseObject> messageString, ParseException e) {
                        if (e == null) {
                            Log.d("Parse", "Message's Retrieved:" + messageString.size());
                            for (int i = 0; i < messageString.size(); i++) {
                                System.out.println(messageString.get(i).get("messageString"));
                            }
                        } else {
                            Log.d("Parse", "Error: " + e.getMessage());
                        }
                    }
                });
            }
        });
    }
}
