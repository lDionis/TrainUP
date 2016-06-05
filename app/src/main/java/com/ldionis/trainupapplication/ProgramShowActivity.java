package com.ldionis.trainupapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ldionis.trainupapplication.adapter.ProgramExcerisesAdapter;
import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.Excercise;

import java.util.List;

public class ProgramShowActivity extends AppCompatActivity {
    private ListView lvExcList;

    private List<Excercise> mProgrExcerciseList;
    private DatabaseHelper mdDBHelper;
    private ProgramExcerisesAdapter adapter;
    private int weekDay;
    private String progName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_show);
        lvExcList = (ListView) findViewById(R.id.programExcercise);
        mdDBHelper = new DatabaseHelper(this);
        progName = getIntent().getStringExtra("ProgramName");
        setTitle(progName);
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Понеділок");
        adapter  = new ProgramExcerisesAdapter(this, mProgrExcerciseList);
        lvExcList.setAdapter(adapter);

     ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        btnMn.setImageResource(R.drawable.monday_active_24px);


    }
    public void onMondayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Понеділок");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_active_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onTuesdayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Вівторок");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_active_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onWednesdayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Середа");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_active_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onThursdayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Четвер");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_active_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onFridayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Пятниця");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_active_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onSaturdayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Субота");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_active_24px);
        btnSu.setImageResource(R.drawable.sunday_passive_24px);
    }

    public void onSundayClick(View v) {
        mProgrExcerciseList = mdDBHelper.getProgramsExcercisesList(progName,"Неділя");
        adapter.updateList(mProgrExcerciseList);
        ImageButton btnMn =(ImageButton)findViewById(R.id.mondayBtn);
        ImageButton btnTu =(ImageButton)findViewById(R.id.tuesdayBtn);
        ImageButton btnWe =(ImageButton)findViewById(R.id.wednesdayBtn);
        ImageButton btnTh =(ImageButton)findViewById(R.id.thursdayBtn);
        ImageButton btnFr =(ImageButton)findViewById(R.id.fridayBtn);
        ImageButton btnSt =(ImageButton)findViewById(R.id.saturdayBtn);
        ImageButton btnSu =(ImageButton)findViewById(R.id.sundayBtn);
        btnMn.setImageResource(R.drawable.monday_passive_24px);
        btnTu.setImageResource(R.drawable.tuesday_passive_24px);
        btnWe.setImageResource(R.drawable.wednesday_passive_24px);
        btnTh.setImageResource(R.drawable.thursday_passive_24px);
        btnFr.setImageResource(R.drawable.friday_passive_24px);
        btnSt.setImageResource(R.drawable.saturday_passive_24px);
        btnSu.setImageResource(R.drawable.sunday_active_24px);
    }
}