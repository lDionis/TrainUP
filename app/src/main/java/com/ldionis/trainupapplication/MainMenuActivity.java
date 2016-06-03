package com.ldionis.trainupapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.AddProgramActivity;
import com.ldionis.trainupapplication.model.ProgramsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainMenuActivity extends Activity {
    DatabaseHelper myDataBaseHelper = new DatabaseHelper(MainMenuActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists())
        {
            myDataBaseHelper.getReadableDatabase();
            //copy db
            if(copyDatabase(this))
            {

            }
            else{


            }
        }
    }

    private boolean copyDatabase(Context context)
    {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte []buff = new byte[1024];
            int length = 0;
            while ((length=inputStream.read(buff))>0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("ExcercisesActivity","DB copied");
            return  true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
        Intent i = new Intent(MainMenuActivity.this, TimerActivity.class);
        startActivity(i);
    }
    public void onButtonClickWater(View v) {
        Intent i = new Intent(MainMenuActivity.this, WaterControllActivity.class);
        startActivity(i);
    }}