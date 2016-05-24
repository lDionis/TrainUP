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

import java.util.ArrayList;

/**
 * Created by PC on 22.05.2016.
 */
public class WeekdayPagerAdapter extends PagerAdapter {
   public ArrayList<String>  selectedItems=new ArrayList<>();
    private Context mContext;
    public static String ffggg;
  //  DatabaseHelper myDataBaseHelper = new DatabaseHelper(mContext);
    public WeekdayPagerAdapter(Context context) {
        mContext = context;
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
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];

      final  LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
        collection.addView(layout);
        final ViewGroup prname = (ViewGroup) inflater.inflate(R.layout.activity_add_program, null);
        collection.addView(prname);
        //----
        final String[] items = {"Жим лежачи","Хаммер","Станова тяга","Французький жим"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.row_layout, R.id.txt_lan,items);
        final ListView chl=(ListView)layout.findViewById(R.id.checkable_list);

        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //----
        chl.setAdapter(adapter);
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public  void onItemClick(AdapterView<?> parent, final View view, final int position, long id)
            {

                final String selectedItem=((TextView)view).getText().toString();
                //final String pr_name=((EditText)view).getText().toString();
                //check / uncheck item
                if(selectedItems.contains(selectedItem))
                {
                    selectedItems.remove(selectedItem);
                }
                else selectedItems.add(selectedItem);

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

                final ViewGroup dialogView = (ViewGroup) inflater.inflate(R.layout.addingwr_dialog, null);
                final EditText ffd=(EditText)prname.findViewById(R.id.programName);
                dialogBuilder.setView(dialogView);

                ffggg = ffd.getText().toString();
                final EditText edt = (EditText) dialogView.findViewById(R.id.email);
                final EditText edtX = (EditText) dialogView.findViewById(R.id.password);

                dialogBuilder.setTitle("Введіть дані вправи");

                dialogBuilder.setPositiveButton("Зберегти", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //do something with edt.getText().toString();
                        String ggg=ffggg;
                        String txt ="Програма:"+ffggg+"\nВправа: "+selectedItem+"\nВага: " + edt.getText().toString()+"\nКількість повторень" + edtX.getText().toString();
                        //Toast.makeText(mContext,"Програма:"+ffggg,Toast.LENGTH_LONG).show();
                        Toast.makeText(mContext,txt,Toast.LENGTH_LONG).show();
                        // myDataBaseHelper.insertProgramItem();
                    }
                });
                dialogBuilder.setNegativeButton("Відмінити", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //pass
                        dialog.dismiss();
                    }
                });

                //----

                final AlertDialog b = dialogBuilder.create();
                b.show();

            }

        });

        /*Button bt= (Button)layout.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pg =progName.getText().toString();
                Toast.makeText(mContext,"\nYou have selected \n"+pg,Toast.LENGTH_SHORT).show();
            }
        });*/

        TextView ff=(TextView) layout.findViewById(R.id.textViewX);
        ff.setText(mContext.getString(customPagerEnum.getTitleResId()));
        return layout;

    }


 //   public void showChangeLangDialog(){



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
