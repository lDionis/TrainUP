package com.ldionis.trainupapplication.model;

/**
 * Created by PC on 16.05.2016.
 */
public class Excercise {
    private int id;
    private  String excercise;
    private  String repeat_amount;
    private  String sets;


    public Excercise(int id,String excercise, String sets, String repeat_amount) {
        this.id = id;
        this.excercise = excercise;
        this.sets = sets;
        this.repeat_amount = repeat_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExcercise() {
        return excercise;
    }

    public void setExcercise(String excercise) {
        this.excercise = excercise;
    }

    public String getRepeat_amount() {
        return repeat_amount;
    }

    public void setRepeat_amount(String repeat_amount) {
        this.repeat_amount = repeat_amount;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }
}
