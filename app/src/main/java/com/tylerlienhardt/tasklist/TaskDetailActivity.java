package com.tylerlienhardt.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //creating the "up" button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        //loading the task name into the text field
        final Intent intent = getIntent();
        String taskName = intent.getStringExtra("taskName");
        EditText nameText = findViewById(R.id.task_name_field);
        nameText.setText(taskName);

        //loading task description into text field
        String taskDesc = intent.getStringExtra("taskDesc");
        EditText descText = findViewById(R.id.task_desc_field);
        descText.setText(taskDesc);
    }

    //creating the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task_detail, menu);
        return true;
    }

    //creating the menu bar button actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_button:

                EditText savedNameText = findViewById(R.id.task_name_field);
                EditText savedDescText = findViewById(R.id.task_desc_field);
                int position = getIntent().getIntExtra("position", -1);

                //checking for empty task name
                if (savedNameText.getText().toString().matches("")) {
                    Toast.makeText(this, "You must enter a task name.", Toast.LENGTH_SHORT).show();

                    return true;
                }

                Toast.makeText(TaskDetailActivity.this, "Saved!", Toast.LENGTH_SHORT).show();

                //sending saved data back to taskListActivity
                Intent saveIntent = new Intent(TaskDetailActivity.this, TaskListActivity.class);
                saveIntent.putExtra("position", position);
                saveIntent.putExtra("taskName", savedNameText.getText().toString());
                saveIntent.putExtra("taskDesc", savedDescText.getText().toString());

                setResult(RESULT_OK, saveIntent);
                finish();

                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);

                return true;

            default:
                Toast.makeText(this, "Error :(", Toast.LENGTH_SHORT).show();

                return true;
        }

    }

}
