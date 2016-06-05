package com.ldionis.trainupapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.AddProgramActivity;

import java.util.Calendar;

public class WaterSettingsActivity extends AppCompatActivity {
    DatabaseHelper mDBHelper= new DatabaseHelper(this);
    private RadioGroup radioSexGroup;
    public int coefficient=0;
    public int weight=0;
    public int waterAmountNum=0;
    public  int notifCheck=0;
    public  EditText amountWater;
    SwitchCompat switchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Налаштування параметрів");

        //----
        notifCheck =mDBHelper.getWaterNotifCheck();
        switchButton = (SwitchCompat) findViewById(R.id.switchButton);
        if(notifCheck==0)
        {switchButton.setChecked(false);
        }
        else switchButton.setChecked(true);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    notifCheck=1;
                } else {
                    notifCheck=0;
                }
            }
        });

        if (switchButton.isChecked()) {
            notifCheck=1;
        } else {
            notifCheck=0;
        }
        RadioButton radioSexMale = (RadioButton) findViewById(R.id.radioMale);
        RadioButton radioSexFemale = (RadioButton) findViewById(R.id.radioFemale);
        coefficient=mDBHelper.getWaterSex();
        if(coefficient==35){radioSexMale.setEnabled(true);}
        else radioSexFemale.setEnabled(true);
        waterAmountNum=mDBHelper.getWaterAmount();
        amountWater = (EditText) findViewById(R.id.setWaterAmount);
        amountWater.setText(waterAmountNum+"");
        EditText weightType = (EditText) findViewById(R.id.setWeight);
        weight=mDBHelper.getWaterWeight();
        weightType.setText(weight+"");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addListenerOnButton();
        addListenerOnCheckbox();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterAmountNum = Integer.parseInt(amountWater.getText().toString());
            mDBHelper.updateWater(waterAmountNum,weight,coefficient,notifCheck);
                //----
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if(notifCheck==1)
                {
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_HOUR,pendingIntent);
                }
                else
                {
                    alarmManager.cancel(pendingIntent);
                }
                showToast();
            }
        });

    }
    public void showToast()
    {
        Context context=getApplicationContext();
        LayoutInflater inflater=getLayoutInflater();

        View customToastroot = inflater.inflate(R.layout.cusst_toast, null);

        Toast customtoast=new Toast(context);

        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();

    }
    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final AppCompatCheckBox checkBox = (AppCompatCheckBox)findViewById(R.id.autoCalculate);
                final EditText amountWater = (EditText)findViewById(R.id.setWaterAmount);
                final EditText weightType = (EditText) findViewById(R.id.setWeight);
                if(checkedId == R.id.radioMale){
                coefficient=35;
                    if(checkBox.isChecked()){
                        weight=Integer.parseInt( weightType.getText().toString() );
                        waterAmountNum =weight*coefficient;
                        amountWater.setText(waterAmountNum+"");
                    }else  amountWater.setText("");
                }else if(checkedId == R.id.radioFemale){
                coefficient=31;
                    if(checkBox.isChecked()){
                        weight=Integer.parseInt( weightType.getText().toString() );
                        waterAmountNum =weight*coefficient;
                        amountWater.setText(waterAmountNum+"");
                    }else  amountWater.setText("");
                }

            }

        });
    }

    public void addListenerOnCheckbox(){
        final AppCompatCheckBox checkBox = (AppCompatCheckBox)findViewById(R.id.autoCalculate);
        final EditText amountWater = (EditText)findViewById(R.id.setWaterAmount);
        final EditText weightType = (EditText) findViewById(R.id.setWeight);

                checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    weight=Integer.parseInt( weightType.getText().toString() );
                    waterAmountNum =weight*coefficient;
                    amountWater.setText(waterAmountNum+"");
                    amountWater.setEnabled(false);}
                else {amountWater.setEnabled(true);
                    amountWater.setText("");}
            }
        });
    }
}
