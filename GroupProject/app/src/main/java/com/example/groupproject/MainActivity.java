package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_SNUM = "KEY_SNUM";

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
        String snumStr = snumET.getText().toString();
        if (snumStr.equals("") || validateSNum(snumStr) == false) {
            Toast.makeText(getApplicationContext(), "Incorrect input please follow pattern in name and s# box", Toast.LENGTH_SHORT).show();
            snumET.setText("");
        }
        else {
                Intent otherIni = new Intent(this, ClassesPage.class);
                otherIni.putExtra(KEY_SNUM, snumStr);
                startActivity(otherIni);

                Log.d("Navigation", "Started other activity");
        }
    }
    public static boolean validateName (String txt) {
        String regex = "^[\\p{L}\\s.’\\-,]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public static boolean validateSNum (String txt){
        for(char c: txt.toCharArray()){
            if(!(Character.isDigit(c) || c == 's')){
                return false;
            }
        }
        return true;
    }

}