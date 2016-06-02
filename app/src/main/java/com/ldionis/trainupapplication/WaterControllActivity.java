package com.ldionis.trainupapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;

import java.util.Calendar;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WaterControllActivity extends AppCompatActivity {
    DatabaseHelper mDBHelper= new DatabaseHelper(this);
public  int consNum=0;
public  int drinked=0;
public  int waterAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_controll);
        setTitle("Прийом води");

        //--------------------------------
        Calendar c = Calendar.getInstance();
        int thisDay = c.get(Calendar.DAY_OF_YEAR); //You can chose something else to compare too, such as DATE..
        long todayMillis = c.getTimeInMillis(); //We might need this a bit later.

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long last = prefs.getLong("date", 0); //If we don't have a saved value, use 0.
        c.setTimeInMillis(last);
        int lastDay = c.get(Calendar.DAY_OF_YEAR);

        if( last==0 || lastDay != thisDay ){
            //New day, update TextView and preference:
            mDBHelper.updateDrinked(0,0);
            consNum=0;
            drinked=0;
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong("date", todayMillis);
            edit.commit();
        }
        //--------------------------------

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setCenterTitleColor(Color.BLACK);
        mWaveLoadingView.setBottomTitleSize(18);
        waterAmount = mDBHelper.getWaterAmount();
        mWaveLoadingView.setCenterTitle(mDBHelper.getWaterDrinkedFull()+" / "+ waterAmount+" мл");
        mWaveLoadingView.setProgressValue(mDBHelper.getWaterDrinker());
        mWaveLoadingView.setBorderWidth(20);
        mWaveLoadingView.setAmplitudeRatio(60);

    }
    public void onButtonClick2f(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+500;
        double numbb = Math.round((500/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount+" мл");
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>100){drinked=0;consNum=0;
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount+" мл");
            mWaveLoadingView.setProgressValue(0);
            mDBHelper.updateDrinked(0,0);
            showToast();
        }

    }

    public void showToast()
    {
        Context context=getApplicationContext();
        LayoutInflater inflater=getLayoutInflater();

        View customToastroot =inflater.inflate(R.layout.mycustom_toast, null);

        Toast customtoast=new Toast(context);

        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();

    }
    public void sett(View v) {
        Intent i = new Intent(WaterControllActivity.this, WaterSettingsActivity.class);
        startActivity(i);
    }

    public void onButton400(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+400;
        double numbb = Math.round((400/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount+" мл");
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;consNum=0;
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount+" мл");
            mWaveLoadingView.setProgressValue(0);
            mDBHelper.updateDrinked(0,0);
            showToast();
        }
    }
    public void onButton300(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+300;
        double numbb = Math.round((300/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount+" мл");
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;consNum=0;
            mWaveLoadingView.setProgressValue(0);
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount+" мл");
            mDBHelper.updateDrinked(0,0);
            showToast();
        }
    }
    public void onButton150(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+150;
        double numbb = Math.round((150/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount+" мл");
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;consNum=0;
            mWaveLoadingView.setProgressValue(0);
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount+" мл");
            mDBHelper.updateDrinked(0,0);
            showToast();
        }
    }
    public void onButton100(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+100;
        double numbb = Math.round((100/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount+" мл");
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;consNum=0;
            mWaveLoadingView.setProgressValue(0);
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount+" мл");
            mDBHelper.updateDrinked(0,0);
            showToast();
        }
    }
}
