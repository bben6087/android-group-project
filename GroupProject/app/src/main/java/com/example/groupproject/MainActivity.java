package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static String KEY_SNUM = "KEY_SNUM";
    final List<ParseObject> lastSearch = new ArrayList<ParseObject>();
    private boolean isUserPass;
    public static boolean sval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void goClassPage(View v) {
        EditText snumET = findViewById(R.id.snumET);
        final String snumStr = snumET.getText().toString();
        EditText passwordET = findViewById(R.id.passwordET);
        final String passwordStr = passwordET.getText().toString();
        if (snumStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(getApplicationContext(), "Input Cannot Be Blank.", Toast.LENGTH_SHORT).show();
        }
        else {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Login");
                Log.d("Click", "You have clicked");
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
                                if (user.get("username").equals(snumStr) && user.get("password").equals(passwordStr)) {
                                    isUserPass = true;
                                    isUser(isUserPass);
                                    break;
                                }
                                else{
                                    isUserPass = false;
                                }

                            }
                            if(isUserPass == false){
                                Toast.makeText(getApplicationContext(), "Incorrect S# or Password.", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });
        }

    }


    public void goClasses(){
        Intent otherIni = new Intent(this, ClassesPage.class);
        EditText snumET = findViewById(R.id.snumET);
        String snumStr = snumET.getText().toString();
        otherIni.putExtra(KEY_SNUM, snumStr);
        startActivity(otherIni);
    }


    public void goSignUp(View v) {
        Intent otherIni = new Intent(this, SignUp.class);
        startActivity(otherIni);
    }

    public void isUser(boolean isUserPass){
        if(isUserPass == true){
            Toast.makeText(getApplicationContext(), "Login Successful :)", Toast.LENGTH_SHORT).show();
            goClasses();
        }
        else if(isUserPass == false){
        }
    }

}