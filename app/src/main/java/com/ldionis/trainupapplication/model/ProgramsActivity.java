package com.ldionis.trainupapplication.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.ExcerciseDescriptionActivity;
import com.ldionis.trainupapplication.ExcercisesActivity;
import com.ldionis.trainupapplication.ProgramShowActivity;
import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.adapter.ListProgramAdapter;
import com.ldionis.trainupapplication.database.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ProgramsActivity extends Activity {

    private ListView lvProgram;
    private ListProgramAdapter adapter;
    private List<Program> mProgramList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_programs);
        lvProgram = (ListView)findViewById(R.id.listview_program);
        mDBHelper = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabProg);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProgramsActivity.this, AddProgramActivity.class);
                startActivity(i);
            }
        });
        //check exists db
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists())
        {
            mDBHelper.getReadableDatabase();
            //copy db
            if(copyDatabase(this))
            {
                Toast.makeText(this,"Copy db success",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Copy db failed",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //get list when db exist
        mProgramList = mDBHelper.getListProgram();
        //Init adapter
        adapter = new ListProgramAdapter(this,mProgramList);
        //set adapter for listview
        lvProgram.setAdapter(adapter);
        lvProgram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String prName = ((TextView) view.findViewById(R.id.tv_program_name)).getText().toString();
                Intent i = new Intent(ProgramsActivity.this, ProgramShowActivity.class);
                i.putExtra("ProgramName",prName);
                startActivity(i);
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
            Log.w("ProgramsActivity","DB copied");
            return  true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
