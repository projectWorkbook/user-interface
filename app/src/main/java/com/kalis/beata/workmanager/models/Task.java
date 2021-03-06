package com.kalis.beata.workmanager.models;

import java.io.Serializable;

/**
 * Created by Beata on 2016-05-13.
 */

// model of task, only for tests
public class Task implements Serializable {

    private long mID;
    private String mName;
    private String mEndDate; // deadline - kiedy musimy wykonac zadanie
    private String mEndTime; // czas trwania zadania
    private int mProgress; // ile czasu juz przeznaczone na to zadanie
    private int mState; // wykonane/niewykonane -> 1/0
    private String mInfo; // opis zadania //+
    private float mDuration ;


    public Task() {
    }

    public Task(String name, String endDate, String endTime, String info) {
        this.mName = name;
        this.mEndDate = endDate;
        this.mEndTime = endTime;
        this.mInfo = info;

        this.mDuration = 0;
        this.mProgress = 0;
        this.mState = 0;
    }

    public void setName(String name){
        this.mName = name;
    }
    public String getName(){
        return this.mName;
    }

    public void setId(long id){
        this.mID = id;
    }
    public long getId(){
        return this.mID;
    }

    public void setDuration(float duration){
        this.mDuration = duration;
    }
    public float getDuration(){
        return this.mDuration;
    }

    public void setEndDate(String date){
        this.mEndDate = date;
    }
    public String getEndDate(){
        return this.mEndDate;
    }

    public void setEndTime(String time){
        this.mEndTime = time;
    }
    public String getEndTime(){
        return this.mEndTime;
    }

    public void setProgress(int progress){
        this.mProgress = progress;
    }
    public int getProgress(){return this.mProgress;}

    public void setState(int state){
        this.mState = state;
    }
    public int getState(){return this.mState;}

    public void setInfo(String info){
        this.mInfo = info;
    }
    public String getInfo(){return this.mInfo;}

    public String toString(){
        return "ID: "+this.mID +"\n"
                +"Name: "+this.mName +"\n"
                +"Duration: "+this.mDuration +"\n"
                +"End Date: "+this.mEndDate +"\n"
                +"End Time: "+this.mEndTime +"\n"
                +"Progress: "+this.mProgress +"\n"
                +"State: "+this.mState+"\n"
                +"Info: "+this.mInfo;
    }

}
