package com.ldionis.trainupapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.AddProgramActivity;
import com.ldionis.trainupapplication.model.ProgramsActivity;

public class MainMenuActivity extends Activity {
    DatabaseHelper myDataBaseHelper = new DatabaseHelper(MainMenuActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    public void onButtonClickExc(View v) {
        Intent i = new Intent(MainMenuActivity.this, ExcercisesActivity.class);
        startActivity(i);
    }

    public void onButtonClickProg(View v) {
        Intent i = new Intent(MainMenuActivity.this, ProgramsActivity.class);
        startActivity(i);
    }

    public void onButtonClickRest(View v) {
        Intent i = new Intent(MainMenuActivity.this, WaterControllActivity.class);
        startActivity(i);
    }

    public void onButtonClickWater(View v) {
        Intent i = new Intent(MainMenuActivity.this, TimerActivity.class);
        startActivity(i);
    }


}