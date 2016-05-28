package com.ldionis.trainupapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.model.Excercise;

import java.util.List;

/**
 * Created by PC on 28.05.2016.
 */
public class ProgramExcerisesAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Excercise> mExcerciseList;

    public ProgramExcerisesAdapter(Context mcontext, List<Excercise> mExcerciseList) {
        this.mcontext = mcontext;
        this.mExcerciseList = mExcerciseList;
    }

    @Override
    public int getCount() {
        if(mExcerciseList == null)
        {
            return 0;
        }
        return mExcerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExcerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mExcerciseList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v= View.inflate(mcontext, R.layout.program_show_items,null);
        TextView excName = (TextView)v.findViewById(R.id.exc_name);
        TextView setsAndReps = (TextView)v.findViewById(R.id.exc_sets_repeats);
        excName.setText(mExcerciseList.get(position).getExcercise());
        setsAndReps.setText("підходів: "+mExcerciseList.get(position).getSets() + " повторень: "+mExcerciseList.get(position).getRepeat_amount());
        return v;
    }
    public void updateList(List<Excercise> lstItem) {
        mExcerciseList.clear();
        mExcerciseList.addAll(lstItem);
        this.notifyDataSetChanged();
}}
