package com.ldionis.trainupapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.model.Program;
import com.ldionis.trainupapplication.model.ProgramsActivity;

import java.util.List;

/**
 * Created by PC on 16.05.2016.
 */
public class ListProgramAdapter extends BaseAdapter{
    private Context mContext;
    private List<Program> mProgramsList;

    public ListProgramAdapter(Context mContext, List<Program> mProgramsList) {
        this.mContext = mContext;
        this.mProgramsList = mProgramsList;
    }

    @Override
    public int getCount() {
        return mProgramsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProgramsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProgramsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvProgramName = (TextView)v.findViewById(R.id.tv_program_name);
        tvProgramName.setText(mProgramsList.get(position).getProgram_name());
        return v;
    }
}
