package com.ldionis.trainupapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldionis.trainupapplication.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by PC on 16.05.2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
   // int images[];
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
   //last param int imgs[]
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData ) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
       // this.images = imgs;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exc_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exc_list_group, null);
        }
       // ImageView myImage = (ImageView) convertView.findViewById(R.id.muscle_group_image);
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        ImageView img=(ImageView) convertView.findViewById(R.id.exc_imagec);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        if(headerTitle =="Плечі")
        {
         img.setImageResource(R.drawable.plechi);
        }
        else if (headerTitle =="Спина")
        {
            img.setImageResource(R.drawable.spyna);
        }
        else if (headerTitle =="Груди")
        {
            img.setImageResource(R.drawable.hrudy);
        }
        else if (headerTitle =="Прес")
        {
            img.setImageResource(R.drawable.pres);
        }
        else if (headerTitle =="Біцепс")
        {
            img.setImageResource(R.drawable.biceps);
        }
        else if (headerTitle =="Трицепс")
        {
            img.setImageResource(R.drawable.triceps);
        }
        else if (headerTitle =="Ноги")
        {
            img.setImageResource(R.drawable.nohy);
        }
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
