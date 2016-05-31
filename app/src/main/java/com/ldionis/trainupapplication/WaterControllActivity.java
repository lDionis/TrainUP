package com.ldionis.trainupapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WaterControllActivity extends AppCompatActivity {
int val=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_controll);
        setTitle("Прийом води");
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        //mWaveLoadingView.setTopTitle("Top Title");
        mWaveLoadingView.setCenterTitleColor(Color.GRAY);
        mWaveLoadingView.setBottomTitleSize(18);
        mWaveLoadingView.setProgressValue(val);
        mWaveLoadingView.setBorderWidth(10);
        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setWaveColor(Color.CYAN);
        mWaveLoadingView.setBorderColor(Color.GRAY);
    }

    public void onButtonClick2f(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+25;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }
}
