package com.ldionis.trainupapplication.model;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.ldionis.trainupapplication.ExcerciseDescriptionActivity;
import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.WaterControllActivity;
import com.ldionis.trainupapplication.adapter.WeekdayPagerAdapter;
import com.ldionis.trainupapplication.database.DatabaseHelper;

import java.util.Random;

public class AddProgramActivity extends AppCompatActivity implements OnAddProgramListener,onDeleteProgramItemListener{
    DatabaseHelper myDataBaseHelper = new DatabaseHelper(this);
    EditText programName;
    @Override
    public void onAddDayExercise(String exercise, String day, String weight, String repeats) {
        myDataBaseHelper.insertProgramItem(programName.getText().toString(), day, exercise, weight,repeats);
    }
    @Override
    public void onDeleteDayItemExercise(String exercise, String day) {
        myDataBaseHelper.deleteProgramItem(programName.getText().toString(),day,exercise);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        setTitle("Створення нової програми");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Random in=new Random();
           int go= in.nextInt();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save_white_24dp);
        programName = (EditText)findViewById(R.id.programName);
        programName.setText("Нова програма X"+go);
        programName.setFocusableInTouchMode(true);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(7);
        viewPager.setAdapter(new WeekdayPagerAdapter(this, this,this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(AddProgramActivity.this, ProgramsActivity.class);
switch (item.getItemId())
    {
        case android.R.id.home:
            finish();
            break;
        case R.id.delete_action:
           myDataBaseHelper.deleteProgram(programName.getText().toString());
           finish();
            break;
    }
        return super.onOptionsItemSelected(item);
    }
}