package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void createAccount(View v){
        EditText snumET = findViewById(R.id.createSNumET);
        String snumStr = snumET.getText().toString();
        EditText passwordET = findViewById(R.id.createPasswordET);
        String passStr = passwordET.getText().toString();
        if(validateSNum(snumStr)== true && !snumStr.equals("") && !passStr.equals("")){
            ParseUser user = new ParseUser();
            user.setUsername(snumStr);
            user.setPassword(passStr);
            user.signUpInBackground(new SignUpCallback() {
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
            if(!(Character.isDigit(c) || c == 's')){
                return false;
            }
        }
        return true;
    }

    public void goToHomePage(View v){
        Intent otherIni = new Intent(this, MainActivity.class);
        startActivity(otherIni);
    }
}