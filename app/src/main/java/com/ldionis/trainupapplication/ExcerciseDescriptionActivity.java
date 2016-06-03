package com.ldionis.trainupapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.ldionis.trainupapplication.database.DatabaseHelper;

public class ExcerciseDescriptionActivity extends AppCompatActivity implements
        YouTubePlayer.OnInitializedListener{
    public static final String DEVELOPER_KEY ="AIzaSyBKO6Yh22o6nvIk2kgJrOrcVPUFB7a75e4";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public String VIDEO_ID = "fhWaJi1Hsfo";
    YouTubePlayerFragment myYouTubePlayerFragment;
    public String excerciseName;
    DatabaseHelper myDataBaseHelper = new DatabaseHelper(ExcerciseDescriptionActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_description);
        excerciseName = getIntent().getStringExtra("ExcerciseName");
        setTitle(excerciseName);

        VIDEO_ID = myDataBaseHelper.getDescriptionVideo(excerciseName);

        myYouTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDataBaseHelper.openDatabase();
        String text =  myDataBaseHelper.getDescription(excerciseName); //this is the method to query
        myDataBaseHelper.closeDatabase();
ImageView dschead = (ImageView)findViewById(R.id.descheader);

        String header = myDataBaseHelper.getDescriptionHeader(excerciseName);
        int headerID = getResources().getIdentifier(header , "drawable", getPackageName());
        dschead.setImageResource(headerID);


        TextView tv = (TextView)findViewById(R.id.exc_description_text);
        tv.setText(text);

        //----
        animateImage();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);




    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtubeplayerfragment);
    }

    public void animateImage(){
           String nameImg1 = myDataBaseHelper.getDescriptionAnimater(excerciseName)+"1";
            String nameImg2 = myDataBaseHelper.getDescriptionAnimater(excerciseName)+"2";



        int resID = getResources().getIdentifier(nameImg1 , "drawable", getPackageName());
        int resID2 = getResources().getIdentifier(nameImg2 , "drawable", getPackageName());


        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(resID);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(resID2);

        AnimationDrawable mAnimation = new AnimationDrawable();
        mAnimation.setOneShot(false);
        mAnimation.addFrame(frame1, 1000);
        mAnimation.addFrame(frame2, 1000);

        ImageView imageView =(ImageView)findViewById(R.id.exc_description_animation);
        imageView.setBackgroundDrawable(mAnimation);
        mAnimation.start();
    }
}
