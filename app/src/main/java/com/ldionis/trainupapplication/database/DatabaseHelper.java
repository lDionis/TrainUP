package com.ldionis.trainupapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ldionis.trainupapplication.R;
import com.ldionis.trainupapplication.adapter.ExpandableListAdapter;
import com.ldionis.trainupapplication.model.Excercise;
import com.ldionis.trainupapplication.model.Program;
import com.ldionis.trainupapplication.model.ProgramsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 16.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DBNAME = "trainup_db.db";
    public  static final String DBLOCATION = "/data/data/com.ldionis.trainupapplication/databases/";
    private Context mContext ;
    private  SQLiteDatabase mDatabase;

    public  DatabaseHelper(Context context)
    {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase()
    {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen())
        {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase()
    {
        if(mDatabase != null)
        {
            mDatabase.close();
        }
    }

    public void insertProgramItem(String programName, String day, String exercise, String sets,String repeats) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="INSERT INTO TrainingPrograms(program_name,date,excercise,sets,repeat_amount) VALUES ('"+ programName + "','"+ day + "','"+ exercise + "','"+ sets + "','"+ repeats + "')";
        db.execSQL(sql);
    }

    public void getHeaderImage(String exerciseName) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="select exc_head_img from Excercisese where excercise_name ='"+ exerciseName + "'";
        db.execSQL(sql);
    }

    public void getAnimationImages(String exerciseName) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="select exc_anim_img from Excercisese where excercise_name ='"+ exerciseName + "'";
        db.execSQL(sql);
    }

    public void getVideo(String exerciseName) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="select exc_video from Excercisese where excercise_name ='"+ exerciseName + "'";
        db.execSQL(sql);
    }

    public void deleteProgramItem(String programName, String day, String exercise) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="delete from TrainingPrograms where program_name ='"+ programName + "' and date= '"+ day + "' and excercise ='"+ exercise + "'";
        db.execSQL(sql);
    }

    public void deleteProgram(String programName) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="delete from TrainingPrograms where program_name ='"+ programName + "'";
        db.execSQL(sql);
    }

    public int getWaterAmount() {
        String selectQuery = "select water_amount from WaterIndicator ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        int data = 0;
        if (cursor.moveToFirst()) {
                data=  cursor.getInt(0);
        }
        cursor.close();
        return data;
    }

    public int getWaterWeight() {
        String selectQuery = "select userWeight from WaterIndicator ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        int data = 0;
        if (cursor.moveToFirst()) {
            data=  cursor.getInt(0);
        }
        cursor.close();
        return data;
    }

    public int getWaterSex() {
        String selectQuery = "select userSex from WaterIndicator ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        int data = 0;
        if (cursor.moveToFirst()) {
            data=  cursor.getInt(0);
        }
        cursor.close();
        return data;
    }
    public void updateWater(int waterAmount, int userWeight, int userSex ) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String selectQuery = "update WaterIndicator set water_amount = '"+ waterAmount + "', userWeight= '"+userWeight+"', userSex= '"+userSex+"' ";
         db.execSQL(selectQuery);
    }

    public void updateDrinked(int drinkedAmount, int drinkedFull ) {
        SQLiteDatabase db  = this.getReadableDatabase();
        String selectQuery = "update WaterIndicator set drinkedAmount = '"+ drinkedAmount + "', drinkedAmountFull = '"+ drinkedFull + "' ";
        db.execSQL(selectQuery);
    }

    public int getWaterDrinker() {
        String selectQuery = "select drinkedAmount from WaterIndicator ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        int data = 0;
        if (cursor.moveToFirst()) {
            data=  cursor.getInt(0);
        }
        cursor.close();
        return data;
    }

    public int getWaterDrinkedFull() {
        String selectQuery = "select drinkedAmountFull from WaterIndicator ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        int data = 0;
        if (cursor.moveToFirst()) {
            data=  cursor.getInt(0);
        }
        cursor.close();
        return data;
    }
    public String[] getAllExcercise() {
        String selectQuery = "SELECT * FROM Excercises order by muscle_group";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        String[] array = new String[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex("excercise_name"));
            array[i] = uname;
            i++;
        }
        return array;
    }

    public String getDescription(String excerciseName) {
        String selectQuery = "SELECT  exc_description FROM Excercises where excercise_name = '"+ excerciseName + "' ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        String data      = null;
        if (cursor.moveToFirst()) {
            do {
                data=cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public String getDescriptionAnimater(String excerciseName) {
        String selectQuery = "SELECT  exc_anim_img FROM Excercises where excercise_name = '"+ excerciseName + "' ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        String data      = null;
        if (cursor.moveToFirst()) {
            do {
                data=cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public String getDescriptionHeader(String excerciseName) {
        String selectQuery = "SELECT  exc_head_img FROM Excercises where excercise_name = '"+ excerciseName + "' ";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery,null);
        String data      = null;
        if (cursor.moveToFirst()) {
            do {
                data=cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public List<Program> getListProgram()
    {
        Program program = null;
        List<Program> productList=new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from TrainingPrograms group by program_name ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            program = new Program(cursor.getInt(0),cursor.getString(1));
            productList.add(program);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return  productList;
    }
    public List<Excercise> getProgramsExcercisesList(String programName, String weekDay)
    {
        Excercise excercise = null;
        List<Excercise> programExcercisesList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from TrainingPrograms where program_name='"+programName+"' and date='"+weekDay+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            excercise = new Excercise(cursor.getInt(0),cursor.getString(3),cursor.getString(4),cursor.getString(5));
           programExcercisesList.add(excercise);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return  programExcercisesList;
    }
}


