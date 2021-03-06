package com.tylerlienhardt.tasklist;

/**
 * Created by Tyler on 3/3/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TaskListActivity extends AppCompatActivity {

    ArrayList<Task> tasks = new ArrayList<>();

    private RecyclerView recyclerView;
    private TaskListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private final int TASK_EDIT_REQUEST_CODE = 0;
    private final int NEW_TASK_REQUEST_CODE = 1;
    static final int RESULT_DELETE = 2;
    static final int RESULT_NEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //loading sample tasks
        createInitialTaskList();

        // Populating the list view with tasks
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaskListAdapter(this, tasks);
        recyclerView.setAdapter(adapter);

        // The + button (new task) action
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
                Task task = tasks.get(position);

                String taskName = intent.getStringExtra("taskName");
                task.setName(taskName);

                String taskDesc = intent.getStringExtra("taskDesc");
                task.setDesc(taskDesc);

                task.setDone(intent.getBooleanExtra("isDone", true));

                task.setMonth(intent.getIntExtra("month", 0));
                task.setDay(intent.getIntExtra("day", 0));
                task.setYear(intent.getIntExtra("year", 0));

                //updating the ListView
                adapter.notifyDataSetChanged();
            }
        }

        //User pressed 'Delete' in task detail activity
        if (requestCode == TASK_EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_DELETE) {

                int position = intent.getIntExtra("position", -1);

                Task task = tasks.get(position);

                tasks.remove(task);
                adapter.notifyItemRemoved(position);

            }
        }

        if (requestCode == NEW_TASK_REQUEST_CODE) {
            if (resultCode == RESULT_NEW) {

                //building the new task
                String taskName = intent.getStringExtra("taskName");
                String taskDesc = intent.getStringExtra("taskDesc");

                int month = intent.getIntExtra("month", 0);
                int day = intent.getIntExtra("day", 0);
                int year = intent.getIntExtra("year", 0);

                Task newTask = new Task(taskName, taskDesc, month, day, year);

                //adding new task to ArrayList and updating ListView
                tasks.add(newTask);
                adapter.notifyDataSetChanged();

            }
        }

    }
    @Override
    public void onResume(){
           super.onResume();
           adapter.notifyDataSetChanged();
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

    //populating the list with sample tasks
    public void createInitialTaskList(){
        tasks.add(new Task("task1", "desc1", 3, 1, 2018));
        tasks.add(new Task("task2", "desc2", 3, 2, 2018));
        tasks.add(new Task("task3 with a very very extremely super duper looong name", "desc3", 3, 3, 2018));
        tasks.add(new Task("task4", "desc4", 3, 4, 2018));
    }
}
