package com.ldionis.trainupapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.adapter.ExpandableListAdapter;
import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.Excercise;
import com.ldionis.trainupapplication.model.Program;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class ExcercisesActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private DatabaseHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercises);
        setTitle("Вправи");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        //------
        mDBHelper = new DatabaseHelper(this);

        //check exists db
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists())
        {
            mDBHelper.getReadableDatabase();
            //copy db
            if(copyDatabase(this))
            {

            }
            else{
                return;
            }
        }
        //------
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            String sds = listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition);
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + sds , Toast.LENGTH_SHORT)
                        .show();
                Intent i = new Intent(ExcercisesActivity.this, ExcerciseDescriptionActivity.class);
                i.putExtra("ExcerciseName",sds);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                return false;
            }
        });
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
    private void prepareListData() {
        listDataChild = new HashMap<String, List<String>>();
        // Adding muscle_groups
        listDataHeader = new ArrayList<String>();
        listDataHeader.add("Плечі");
        listDataHeader.add("Спина");
        listDataHeader.add("Груди");
        listDataHeader.add("Прес");
        listDataHeader.add("Біцепс");
        listDataHeader.add("Трицепс");
        listDataHeader.add("Ноги");
        // Adding excercises
        List<String> plechi = Arrays.asList(getResources().getStringArray(R.array.exc_plechi));
        List<String> spyna = Arrays.asList(getResources().getStringArray(R.array.exc_spyna));
        List<String> hrudy = Arrays.asList(getResources().getStringArray(R.array.exc_hrudy));
        List<String> pres = Arrays.asList(getResources().getStringArray(R.array.exc_pres));
        List<String> biceps = Arrays.asList(getResources().getStringArray(R.array.exc_biceps));
        List<String> triceps = Arrays.asList(getResources().getStringArray(R.array.exc_triceps));
        List<String> nohy = Arrays.asList(getResources().getStringArray(R.array.exc_nohy));
        listDataChild.put(listDataHeader.get(0),plechi );
        listDataChild.put(listDataHeader.get(1), spyna);
        listDataChild.put(listDataHeader.get(2), hrudy);
        listDataChild.put(listDataHeader.get(3), pres);
        listDataChild.put(listDataHeader.get(4), biceps);
        listDataChild.put(listDataHeader.get(5), triceps);
        listDataChild.put(listDataHeader.get(6), nohy);
    }
}
