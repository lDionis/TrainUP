package com.ldionis.trainupapplication;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldionis.trainupapplication.database.DatabaseHelper;

public class ExcerciseDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String excerciseName = getIntent().getStringExtra("ExcerciseName");
        DatabaseHelper myDataBaseHelper = new DatabaseHelper(ExcerciseDescriptionActivity.this);
        myDataBaseHelper.openDatabase();
        String text =  myDataBaseHelper.getDescription(excerciseName); //this is the method to query
        myDataBaseHelper.closeDatabase();
        TextView tv = (TextView)findViewById(R.id.exc_description_text);
        tv.setText(text);
        //----
        animateImage();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void animateImage(){
        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.logo);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.ttiaga2);

        AnimationDrawable mAnimation = new AnimationDrawable();
        mAnimation.setOneShot(false);
        mAnimation.addFrame(frame1, 1000);
        mAnimation.addFrame(frame2, 1000);

        ImageView imageView =(ImageView)findViewById(R.id.exc_description_animation);
        imageView.setBackgroundDrawable(mAnimation);
        mAnimation.start();
    }
}
