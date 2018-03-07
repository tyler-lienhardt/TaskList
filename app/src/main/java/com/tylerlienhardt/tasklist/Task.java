package com.tylerlienhardt.tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 3/3/2018.
 */

public class Task {

    public static List<Task> tasks = new ArrayList<Task>();
    private String name;
    private String desc;
    private boolean completed = false;

    public Task (String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static void createInitialTaskList(){
        tasks.add(new Task("Crack that whip", "When a problem comes along"));
        tasks.add(new Task("Give the past the slip", "You must whip it"));
        tasks.add(new Task("Step on a crack", "Before the cream sits out too long"));
        tasks.add(new Task("Break your momma's back", "You must whip it"));
    }

    @Override public String toString() {
        return this.name;
    }

}
