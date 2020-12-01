package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
    final List<ParseObject> lastSearch = new ArrayList<ParseObject>();
    private boolean canCreate;
    public static boolean sval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public void createAccount(final View v) {
        EditText snumET = findViewById(R.id.createSNumET);
        final String snumStr = snumET.getText().toString();
        EditText passwordET = findViewById(R.id.createPasswordET);
        String passStr = passwordET.getText().toString();
        validateSNum(snumStr);
        if (sval == true && !snumStr.equals("") && !passStr.equals("")) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Login");
            Log.d("Click", "You have clicked");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    lastSearch.clear();
                    lastSearch.addAll(objects);
                    if (e == null) {
                        //success
                        for (int i = 0; i < objects.size(); i++) {
                            ParseObject user = objects.get(i);
                            if (user.get("username").equals(snumStr)) {
                                Toast.makeText(getApplicationContext(), "S# already taken. Try picking a different one.", Toast.LENGTH_LONG).show();
                                canCreate = false;
                                break;
                            }
                            else{
                                canCreate = true;
                            }
                        }
                        if(canCreate==true){
                            EditText snumET = findViewById(R.id.createSNumET);
                            String snumStr = snumET.getText().toString();
                            EditText passwordET = findViewById(R.id.createPasswordET);
                            String passStr = passwordET.getText().toString();
                            ParseObject login = new ParseObject("Login");
                            login.put("username", snumStr);
                            login.put("password", passStr);
                            login.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Log.d("Parse", "Reg: " + e);
                                    Toast.makeText(getApplicationContext(), "You have Successfully created an Account!", Toast.LENGTH_LONG).show();
                                }
                            });
                            goToHomePage(v);
                        }

                    }

                }
            });

        }
        else if(sval == false){
            Toast.makeText(getApplicationContext(), "Incorrect input please follow pattern in s# box.", Toast.LENGTH_LONG).show();
        }
        else if(snumStr.equals("") || passStr.equals("")){
            Toast.makeText(getApplicationContext(), "Input Cannot Be Blank.", Toast.LENGTH_LONG).show();
        }
    }

    public static void validateSNum (String txt){
        for(char c: txt.toCharArray()){
            if(Character.isDigit(c) || c == 's'){
                sval = true;
            }
            else{
                sval = false;
                break;
            }

        }
    }

    public void goToHomePage(View v){
        Intent otherIni = new Intent(this, MainActivity.class);
        startActivity(otherIni);
    }
}