package com.ldionis.trainupapplication.model;

/**
 * Created by PC on 16.05.2016.
 */
public class Excercise {
    private  String exc_description;

    public Excercise(String exc_description) {

        this.exc_description = exc_description;
    }

    public String getExc_description() {
        return exc_description;
    }

    public void setExc_description(String exc_description) {
        this.exc_description = exc_description;
    }
}
