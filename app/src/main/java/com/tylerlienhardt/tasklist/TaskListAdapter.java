package com.tylerlienhardt.tasklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Tyler on 3/21/2018.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    ArrayList<Task> tasks;
    Context context;

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.context = context;
    }

    // Provide a reference to the views for each data item.
    // Complex data items may need more than one view per item,
    // and you provide access to all the views for a data item in a view holder.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView checkBoxView;
        private TextView nameLabel;
        private TextView dateLabel;



        public ViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);

            checkBoxView = itemView.findViewById(R.id.checkbox_button);
            nameLabel = itemView.findViewById(R.id.task_name);
            dateLabel = itemView.findViewById(R.id.task_date);
        }

        public void bind(Task task) {
            final Task mTask = task;

            nameLabel.setText(task.getName());

            dateLabel.setText(task.dateAsString());

            //creating and setting the clickable checkbox
            if (task.isDone() == true) {
                checkBoxView.setImageResource(R.drawable.checked_box);
            } else {
                checkBoxView.setImageResource(R.drawable.unchecked_box);
            }

            checkBoxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mTask.isDone() == false) {
                        mTask.setDone(true);
                        checkBoxView.setImageResource(R.drawable.checked_box);
                    }
                    else {
                        mTask.setDone(false);
                        checkBoxView.setImageResource(R.drawable.unchecked_box);
                    }

//                    Collections.sort(tasks);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TaskDetailActivity.class);
            int position = getAdapterPosition();
            Task task = tasks.get(position);
            intent.putExtra("taskName", task.getName());
            intent.putExtra("taskDesc", task.getDesc());
            intent.putExtra("position", position);
            intent.putExtra("isDone", task.isDone());
            intent.putExtra("month", task.getMonth());
            intent.putExtra("day", task.getDay());
            intent.putExtra("year", task.getYear());

            ((Activity)context).startActivityForResult(intent, 0);
        }
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Task task = tasks.get(position);
        holder.bind(task);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
