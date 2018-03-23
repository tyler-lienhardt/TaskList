package com.tylerlienhardt.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



/**
 * Created by Tyler on 3/21/2018.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {
    public TaskListAdapter(Context context, ArrayList<Task> tasks, View.OnClickListener clickListener) {
        super(context, 0, tasks);
        this.checkListener = checkListener;
    }

    private View.OnClickListener checkListener;

    public void setCheckListener(View.OnClickListener clickListener) {
        this.checkListener = checkListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }
        //really do this on image view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.checkbox_button);
        imageView.setOnClickListener(checkListener);

        // Lookup view for data population
        TextView nameLabel = (TextView) convertView.findViewById(R.id.task_name);
        nameLabel.setText(task.getName());

        TextView dateLabel = (TextView) convertView.findViewById(R.id.task_date);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateLabel.setText(dateFormat.format(task.getDate()));


        return convertView;
    }

}
