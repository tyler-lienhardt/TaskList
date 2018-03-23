package com.tylerlienhardt.tasklist;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tyler on 3/3/2018.
 */

public class Task implements Comparable<Task>{

    private String name;
    private String desc;
    private Date date;
    private boolean isDone = false;

    public Task (String name, String desc, Date date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override public String toString() {
        return this.name;
    }

    //compareTo sorts by isDone state first, then by date
    public int compareTo(Task task) {
        if (this.isDone == false && task.isDone() == false) {
            return this.date.compareTo(task.getDate());
        }
        else if (this.isDone == false && task.isDone() == true) {
            return 1;
        }
        else if (this.isDone == true && task.isDone() == false) {
            return -1;
        }
        else {
            return this.date.compareTo(task.getDate());
        }
    }

}
