package com.ldionis.trainupapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ldionis.trainupapplication.database.DatabaseHelper;

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
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setCenterTitleColor(Color.GRAY);
        mWaveLoadingView.setBottomTitleSize(18);
        waterAmount = mDBHelper.getWaterAmount();
        mWaveLoadingView.setCenterTitle(mDBHelper.getWaterDrinkedFull()+" / "+ waterAmount);
        mWaveLoadingView.setProgressValue(mDBHelper.getWaterDrinker());
        mWaveLoadingView.setBorderWidth(20);
        mWaveLoadingView.setAmplitudeRatio(60);

        //mWaveLoadingView.setWaveColor(Color.CYAN);
       // mWaveLoadingView.setBorderColor(Color.GRAY);



    }
    public void onButtonClick2f(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+500;
        double numbb = Math.round((500/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount);
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>100){drinked=0;
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount);
            mWaveLoadingView.setProgressValue(0);
            mDBHelper.updateDrinked(0,0);
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте у тому ж дусі!",Toast.LENGTH_LONG).show();
        }

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
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount);
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount);
            mWaveLoadingView.setProgressValue(0);
            mDBHelper.updateDrinked(0,0);
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
    }
    public void onButton300(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+300;
        double numbb = Math.round((300/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount);
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;
            mWaveLoadingView.setProgressValue(0);
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount);
            mDBHelper.updateDrinked(0,0);
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
    }
    public void onButton150(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+150;
        double numbb = Math.round((150/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount);
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;
            mWaveLoadingView.setProgressValue(0);
            mWaveLoadingView.setCenterTitle(0+" / "+ waterAmount);
            mDBHelper.updateDrinked(0,0);
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
    }
    public void onButton100(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        consNum=consNum+100;
        double numbb = Math.round((100/(double) waterAmount) * 100.0);
        drinked= (int) (drinked+Math.round(numbb));
        mWaveLoadingView.setProgressValue(drinked);
        mWaveLoadingView.setCenterTitle(consNum+" / "+ waterAmount);
        mDBHelper.updateDrinked(drinked,consNum);
        if(drinked>=100){drinked=0;
            mWaveLoadingView.setProgressValue(0);
            mDBHelper.updateDrinked(0,0);
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
    }
}
