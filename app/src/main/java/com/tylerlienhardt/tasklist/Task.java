package com.tylerlienhardt.tasklist;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tyler on 3/3/2018.
 */

public class Task implements Comparable<Task> {

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

    public static Date stringToDate (String string) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(string);
    }

    public static String dateToString (Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(date);
    }

    //FIXME work in progress
    /*
    public static ImageView displayCheckBox(final Context context, final Task task) {
        final ImageView checkBoxView = new ImageView(context).findViewById(R.id.checkbox_button);

        if (task.isDone() == false) {
            checkBoxView.setImageResource(R.drawable.unchecked_box);
            return checkBoxView;
        }
        else {
            checkBoxView.setImageResource(R.drawable.checked_box);
            return checkBoxView;
        }

        checkBoxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (task.isDone() == false) {
                    task.setDone(true);
                    checkBoxView.setImageResource(R.drawable.checked_box);
                }
                else {
                    task.setDone(false);
                    checkBoxView.setImageResource(R.drawable.unchecked_box);
                }

//                    Collections.sort(tasks);
//                notifyDataSetChanged();

                System.out.println("CHECKBOX CLICKED"); //FIXME
                System.out.println(task.getName() + " isDone STATE CHANGED TO: " + task.isDone());
            }
        });
    }
    */

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
