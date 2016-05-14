package com.kalis.beata.workmanager.models;

/**
 * Created by Beata on 2016-05-13.
 */

// model of task, only for tests
public class Task {
    private int ID;
    private String name;
    private String date;

    public Task(String name, String date) {
        this.name = name;
        this.date = date;

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
