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
        mWaveLoadingView.setCenterTitleColor(Color.GRAY);
        mWaveLoadingView.setBottomTitleSize(18);
        mWaveLoadingView.setProgressValue(val);
        mWaveLoadingView.setBorderWidth(20);
        mWaveLoadingView.setAmplitudeRatio(60);
        //mWaveLoadingView.setWaveColor(Color.CYAN);
       // mWaveLoadingView.setBorderColor(Color.GRAY);
    }
    public void onButtonClick2f(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+25;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }

    public void onButton400(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+20;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }
    public void onButton300(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+15;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }
    public void onButton150(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+7;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }
    public void onButton100(View v) {
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        val=val+52;
        if(val>100){val=0;
            Toast.makeText(this,"Ви випили достатньо на сьогодні.Продовжуйте завтра у тому ж дусі!",Toast.LENGTH_LONG).show();
        }
        mWaveLoadingView.setProgressValue(val);
    }
}
