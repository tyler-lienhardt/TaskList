package com.tylerlienhardt.tasklist;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tyler on 3/3/2018.
 */

public class Task implements Comparable<Task> {

    private String name;
    private String desc;

    private int month;
    private int day;
    private int year;

    private boolean isDone = false;

    public Task (String name, String desc, int month, int day, int year) {
        this.name = name;
        this.desc = desc;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String dateAsString() {
        return (month + 1) + "-" + day + "-" + year;
    }

    @Override public String toString() {
        return this.name;
    }

    //FIXME implement sorting by date
    //compareTo sorts by isDone state first, then by date
    public int compareTo(Task task) {
        if (this.isDone == false && task.isDone() == false) {
            //return this.date.compareTo(task.getDate());
            return 0;
        }
        else if (this.isDone == false && task.isDone() == true) {
            return 1;
        }
        else if (this.isDone == true && task.isDone() == false) {
            return -1;
        }
        else {
            //return this.date.compareTo(task.getDate());
            return 0;
        }
    }
}
