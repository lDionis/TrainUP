package com.ldionis.trainupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.AddProgramActivity;

public class WaterSettingsActivity extends AppCompatActivity {
    DatabaseHelper mDBHelper= new DatabaseHelper(this);
    private RadioGroup radioSexGroup;
    public int coefficient=0;
    public int weight=0;
    public int waterAmountNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Параметри");
        RadioButton radioSexMale = (RadioButton) findViewById(R.id.radioMale);
        RadioButton radioSexFemale = (RadioButton) findViewById(R.id.radioFemale);
        coefficient=mDBHelper.getWaterSex();
        if(coefficient==35){radioSexMale.setEnabled(true);}
        else radioSexFemale.setEnabled(true);
        waterAmountNum=mDBHelper.getWaterAmount();
        EditText amountWater = (EditText) findViewById(R.id.setWaterAmount);
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
            mDBHelper.updateWater(waterAmountNum,weight,coefficient);
                Toast.makeText(WaterSettingsActivity.this,"Параметри оновлено",Toast.LENGTH_LONG).show();
            }
        });

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
