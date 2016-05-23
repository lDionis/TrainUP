package com.ldionis.trainupapplication.model;

/**
 * Created by PC on 16.05.2016.
 */
public class Program {
    private int id;
    private String program_name;
    //private String date;
    //private  String muscle_group;

    public Program(int id, String program_name) {
        this.id = id;
        this.program_name = program_name;
       /* this.date = date;
        this.muscle_group = muscle_group;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

   /* public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMuscle_group() {
        return muscle_group;
    }

    public void setMuscle_group(String muscle_group) {
        this.muscle_group = muscle_group;
    }*/
}
