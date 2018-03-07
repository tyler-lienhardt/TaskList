package com.tylerlienhardt.tasklist;

/**
 * Created by Tyler on 3/3/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TaskListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Task> adapter;
    private final int TASK_EDIT_REQUEST_CODE = 0;
    private final int NEW_TASK_REQUEST_CODE = 1;

    List<Task> tasks = Task.tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //loading sample tasks
        Task.createInitialTaskList();

        listView = (ListView) findViewById(R.id.task_list_view);
        adapter = new ArrayAdapter<Task>(this,
                android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
                Task task = (Task)listView.getAdapter().getItem(position);
                String taskName = task.getName();
                String taskDesc = task.getDesc();
                intent.putExtra("taskName", taskName);
                intent.putExtra("taskDesc", taskDesc);
                intent.putExtra("position", position);
                startActivityForResult(intent, TASK_EDIT_REQUEST_CODE);
            }
        });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TaskListActivity.this, NewTaskActivity.class);
                startActivityForResult(intent, NEW_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent intent){

        if (requestCode == TASK_EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                //updating the edited task
                int position = intent.getIntExtra("position", -1);
                Task task = (Task)listView.getAdapter().getItem(position);

                String taskName = intent.getStringExtra("taskName");
                task.setName(taskName);

                String taskDesc = intent.getStringExtra("taskDesc");
                task.setDesc(taskDesc);

                //updating the ListView
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode == NEW_TASK_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                //building the new task
                String taskName = intent.getStringExtra("taskName");
                String taskDesc = intent.getStringExtra("taskDesc");

                Task newTask = new Task(taskName, taskDesc);

                //adding new task to ArrayList and updating ListView
                tasks.add(newTask);
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
