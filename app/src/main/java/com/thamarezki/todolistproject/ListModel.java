package com.thamarezki.todolistproject;

public class ListModel {
    String planact,description,date,time;
    int id;
    boolean isDone;


    public ListModel(String planact, String description, String date, String time, boolean isDone, int id) {
        this.planact = planact;
        this.description = description;
        this.date = date;
        this.time = time;
        this.isDone = isDone;
        this.id = id;
    }

    public String getPlanact() {
        return planact;
    }

    public void setPlanact(String planact) {
        this.planact = planact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
