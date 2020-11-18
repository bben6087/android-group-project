package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {

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

    public void createAccount(View v) {
        EditText snumET = findViewById(R.id.createSNumET);
        String snumStr = snumET.getText().toString();
        EditText passwordET = findViewById(R.id.createPasswordET);
        String passStr = passwordET.getText().toString();
        if (validateSNum(snumStr) == true && !snumStr.equals("") && !passStr.equals("")) {
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
        else if(validateSNum(snumStr) == false){
            Toast.makeText(getApplicationContext(), "Incorrect input please follow pattern in s# box.", Toast.LENGTH_LONG).show();
        }
        else if(snumStr.equals("") || passStr.equals("")){
            Toast.makeText(getApplicationContext(), "Input Cannot Be Blank.", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean validateSNum (String txt){
        for(char c: txt.toCharArray()){
            if(Character.isDigit(c) || c == 's'){
                return true;
            }
        }
        return false;
    }

    public void goToHomePage(View v){
        Intent otherIni = new Intent(this, MainActivity.class);
        startActivity(otherIni);
    }
}