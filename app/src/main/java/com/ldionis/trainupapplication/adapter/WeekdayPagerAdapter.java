package com.ldionis.trainupapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.PagerAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ldionis.trainupapplication.ExcerciseDescriptionActivity;
import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.database.DatabaseHelper;
import com.ldionis.trainupapplication.model.AddProgramActivity;
import com.ldionis.trainupapplication.model.OnAddProgramListener;
import com.ldionis.trainupapplication.model.onDeleteProgramItemListener;
import java.util.ArrayList;

/**
 * Created by PC on 22.05.2016.
 */
public class WeekdayPagerAdapter extends PagerAdapter {
    public ArrayList<String>  selectedItems=new ArrayList<>();
    private Context mContext;
    private OnAddProgramListener listener;
    private  onDeleteProgramItemListener delListener;

    public WeekdayPagerAdapter(Context context, OnAddProgramListener listener,onDeleteProgramItemListener delListener) {
        mContext = context;
        this.listener = listener;
        this.delListener = delListener;

    }
    public enum CustomPagerEnum {

        Monday(R.string.mondey, R.layout.add_program_page_items),
        Tuesday(R.string.tuesday, R.layout.add_program_page_items),
        Wednesday(R.string.wednesday, R.layout.add_program_page_items),
        Thursday(R.string.thursday, R.layout.add_program_page_items),
        Friday(R.string.friday, R.layout.add_program_page_items),
        Saturday(R.string.saturday, R.layout.add_program_page_items),
        Sunday(R.string.sunday, R.layout.add_program_page_items);


        private int mTitleResId;
        private int mLayoutResId;

        CustomPagerEnum(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getLayoutResId() {
            return mLayoutResId;
        }

    }

    @Override
    //тут заповнюємо ліст і решту всього
    public Object instantiateItem(ViewGroup collection, int position) {
        final   CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];

        final  LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
        collection.addView(layout);
        final DatabaseHelper myDataBaseHelper = new DatabaseHelper(mContext);
        myDataBaseHelper.openDatabase();
        final String[] items =  myDataBaseHelper.getAllExcercise(); //this is the method to query

        //----
        //   final String[] items = {"Жим лежачи","Хаммер","Станова тяга","Французький жим","Шраги зі штангою","Тяга блоку за голову сидячи","Гіперекстензія","Жим гантелей сидячи","Армійський жим"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.row_layout, R.id.txt_lan,items);
        final ListView chl=(ListView)layout.findViewById(R.id.checkable_list);
        myDataBaseHelper.closeDatabase();
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //----
        chl.setAdapter(adapter);
        chl.setSelected(false);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public  void onItemClick(AdapterView<?> parent, final View view, final int position, long id)
            {

                final String selectedItem=((TextView)view).getText().toString();

                //check / uncheck item
                if(selectedItems.contains(selectedItem))
                {
                    selectedItems.remove(selectedItem);
                }
                else selectedItems.add(selectedItem);


                final String itemMuscle_group = myDataBaseHelper.getMuscleGroupForProgram(selectedItem);
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

                final ViewGroup dialogView = (ViewGroup) inflater.inflate(R.layout.addingwr_dialog, null);
                dialogBuilder.setView(dialogView);

                final EditText edt = (EditText) dialogView.findViewById(R.id.email);
                final EditText edtX = (EditText) dialogView.findViewById(R.id.password);
                dialogBuilder.setTitle("Введіть дані вправи");
                dialogBuilder.setPositiveButton("Зберегти", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String day_of_week="";
                        switch (mContext.getString(customPagerEnum.getTitleResId()))
                        {
                            case "Понеділок" : day_of_week="Понеділок";break;
                            case "Вівторок" : day_of_week="Вівторок";break;
                            case "Середа" : day_of_week="Середа";break;
                            case "Четвер" : day_of_week="Четвер";break;
                            case "П`ятниця" : day_of_week="Пятниця";break;
                            case "Субота" : day_of_week="Субота";break;
                            case "Неділя" : day_of_week="Неділя";break;
                        }
                        if(edt.getText().toString()=="" || edtX.getText().toString()==""){
                            Toast.makeText(mContext, "Ви не заповнили потрібні поля", Toast.LENGTH_SHORT).show();
                        }
                        else {if (listener != null) {
                            listener.onAddDayExercise(selectedItem, day_of_week, edt.getText().toString(), edtX.getText().toString(),itemMuscle_group);
                        }}
                        chl.setItemChecked(position,true);
                    }
                });
                dialogBuilder.setNegativeButton("Відмінити", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //pass
                        chl.setItemChecked(position,false);
                        dialog.dismiss();
                    }
                });
                String day_of_week="";
                switch (mContext.getString(customPagerEnum.getTitleResId()))
                {
                    case "Понеділок" : day_of_week="Понеділок";break;
                    case "Вівторок" : day_of_week="Вівторок";break;
                    case "Середа" : day_of_week="Середа";break;
                    case "Четвер" : day_of_week="Четвер";break;
                    case "П'ятниця" : day_of_week="П`ятниця";break;
                    case "Субота" : day_of_week="Субота";break;
                    case "Неділя" : day_of_week="Неділя";break;
                }

                if(chl.isItemChecked(position))
                {final AlertDialog b = dialogBuilder.create();
                    b.show();
                }
                else
                { delListener.onDeleteDayItemExercise(selectedItem, day_of_week);
                    chl.setItemChecked(position,false);
                }
            }

        });

        TextView ff=(TextView) layout.findViewById(R.id.textViewX);
        ff.setText(mContext.getString(customPagerEnum.getTitleResId()));
        return layout;

    }
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }
}