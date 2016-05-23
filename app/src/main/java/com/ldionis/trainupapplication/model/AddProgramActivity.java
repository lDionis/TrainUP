package com.ldionis.trainupapplication.model;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.adapter.WeekdayPagerAdapter;

public class AddProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(7);
        viewPager.setAdapter(new WeekdayPagerAdapter(this));

    }

}
