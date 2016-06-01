package com.ldionis.trainupapplication;

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
import android.widget.Toast;

public class WaterSettingsActivity extends AppCompatActivity {

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;
    public int coefficient=0;
    public int weight =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Параметри");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addListenerOnButton();
        addListenerOnCheckbox();
    }
    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radioMale){
                coefficient=35;
                }else if(checkedId == R.id.radioFemale){
                coefficient=31;
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
                    int waterAmountNum =weight*coefficient;
                    amountWater.setText(waterAmountNum+"");
                    amountWater.setEnabled(false);}
                else amountWater.setEnabled(true);
            }
        });
    }
}
