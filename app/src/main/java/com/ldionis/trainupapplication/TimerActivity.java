package com.ldionis.trainupapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class TimerActivity extends AppCompatActivity {
    CountDownTimer   countDownTimer;
public int minuti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle("Час відпочинку");
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ProgressBar pb = (ProgressBar) findViewById(R.id.barTimer);
        pb.setProgress(100);
        TextView tvTimer = (TextView) findViewById(R.id.textTimer);
        tvTimer.setText("02:00");
        minuti = 2;
    }
    public  void onTimeSetClick(View view)
    {
      final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(1)
                .maxValue(60)
                .defaultValue(1)
                .backgroundColor(Color.WHITE)
                .separatorColor(ContextCompat.getColor(this, R.color.orangy))
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();
        new AlertDialog.Builder(this)
                .setTitle("Вкажіть час перерви(хв)")
                .setView(numberPicker)
                .setPositiveButton("Встановити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tvTimer = (TextView) findViewById(R.id.textTimer);
                        if (numberPicker.getValue()>=10){tvTimer.setText(numberPicker.getValue()+":00");}
                        else tvTimer.setText("0"+numberPicker.getValue()+":00");
                         minuti = numberPicker.getValue();
                    }
                })
                .setNegativeButton("Cкасувати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    public  void startTime(View view){
        Button start =(Button) findViewById(R.id.button);
        ImageView setTime = (ImageView)findViewById(R.id.imageView);
        start.setBackgroundResource(R.drawable.buttontimepressed);
        start.setEnabled(false);
        setTime.setEnabled(false);
        startTimer(minuti);
    }
    private void startTimer(final int minuti) {
        countDownTimer = new CountDownTimer(60 * minuti * 1000, 500) {
            // 500 means, onTick function will be called at every 500 milliseconds
            ProgressBar barTimer = (ProgressBar) findViewById(R.id.barTimer);
            TextView textTimer = (TextView) findViewById(R.id.textTimer);
            final MediaPlayer mp = new MediaPlayer();
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                barTimer.setMax(60*minuti);
                barTimer.setProgress((int)seconds);
                textTimer.setText(String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60));
            }
            @Override
            public void onFinish() {
                Button start =(Button) findViewById(R.id.button);
                ImageView setTime = (ImageView)findViewById(R.id.imageView);
                if(textTimer.getText().equals("00:00")){
                    try {
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("alarmsong.mp3");
                        mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        mp.prepare();
                        mp.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    textTimer.setText("00:00");
                    start.setEnabled(true);
                    start.setBackgroundResource(R.drawable.button_break);
                    setTime.setEnabled(true);

                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(70); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    textTimer.startAnimation(anim);
                }
                else{
                    textTimer.setText("2:00");
                    barTimer.setProgress(60*minuti);
                }            }
        }.start();}}
