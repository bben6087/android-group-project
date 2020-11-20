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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

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

        myModel.messages.clear();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
        query.whereMatches("username", ClassesPage.getSnum());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("Parse", "Message's Retrieved:" + objects.size());
                    for (int i = 0; i < objects.size(); i++) {
                        ParseObject message = objects.get(i);
                        myModel.messages.add(
                                new MessengerModel.Message(message.getString("message")));
                        myAdapter.notifyItemChanged(myAdapter.getItemCount() - 1);
                    }
                } else {
                    Log.d("Parse", "Error: " + e.getMessage());
                }


            }
        });

        Button sendBTN = findViewById(R.id.sendBTN);
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
                    ParseObject message = new ParseObject("Message");
                    message.put("message",messageStr);
                    message.put("username", ClassesPage.getSnum());
                    message.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("Parse", "Reg: " + e);
                        }
                    });
                }
            }

        });
    }
}

