package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_SNUM = "KEY_SNUM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goClassPage(View v) {

        EditText nameET = findViewById(R.id.nameET);
        EditText snumET = findViewById(R.id.snumET);
        String nameStr = nameET.getText().toString();
        String snumStr = snumET.getText().toString();
        String[] nameParts = nameStr.split(",");
        if (validateName(nameParts[0]) == false || validateName(nameParts[1]) == false || (nameStr.equals("") || snumStr.equals("")) || validateSNum(snumStr) == false) {
            Toast.makeText(getApplicationContext(), "Incorrect input please follow pattern in name and s# box", Toast.LENGTH_SHORT).show();
            nameET.setText("");
            snumET.setText("");
        }
        else {
                Intent otherIni = new Intent(this, ClassesPage.class);
                otherIni.putExtra(KEY_NAME, nameStr);
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