package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_SNUM = "KEY_SNUM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goClassPage(View v){

        EditText nameET = findViewById(R.id.nameET);
        EditText snumET = findViewById(R.id.snumET);
        String nameStr = nameET.getText().toString();
        String snumStr = snumET.getText().toString();
        if(nameStr.equals("") || snumStr.equals("")){
            Toast.makeText(getApplicationContext(),"Input Cannot Be Blank",Toast.LENGTH_SHORT).show();
            nameET.setText("");
            snumET.setText("");
        }
        else{
            Intent otherIni = new Intent(this, ClassesPage.class);
            otherIni.putExtra(KEY_NAME, nameStr);
            otherIni.putExtra(KEY_SNUM, snumStr);
            startActivity(otherIni);

            Log.d("Navigation", "Started other activity");
        }


    }
}