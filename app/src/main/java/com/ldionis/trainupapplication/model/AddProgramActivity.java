package com.ldionis.trainupapplication.model;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.ldionis.trainupapplication.ExcerciseDescriptionActivity;
import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.adapter.WeekdayPagerAdapter;
import com.ldionis.trainupapplication.database.DatabaseHelper;

public class AddProgramActivity extends AppCompatActivity implements OnAddProgramListener,onDeleteProgramItemListener{

    EditText programName;

    @Override
    public void onAddDayExercise(String exercise, String day, String weight, String repeats) {
          DatabaseHelper myDataBaseHelper = new DatabaseHelper(this);
        myDataBaseHelper.insertProgramItem(programName.getText().toString(), day, exercise, weight,repeats);
    }

    @Override
    public void onDeleteDayItemExercise(String exercise, String day) {
        DatabaseHelper myDataBaseHelper = new DatabaseHelper(this);
        myDataBaseHelper.deleteProgramItem(programName.getText().toString(),day,exercise);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        programName = (EditText)findViewById(R.id.programName);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(7);
        viewPager.setAdapter(new WeekdayPagerAdapter(this, this,this));

    }


}
