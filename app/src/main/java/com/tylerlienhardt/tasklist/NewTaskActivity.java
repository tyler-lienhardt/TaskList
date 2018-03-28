package com.tylerlienhardt.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {
    EditText taskName;
    EditText taskDesc;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //creating the "up" button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        taskName = findViewById(R.id.new_task_field);
        taskDesc = findViewById(R.id.new_desc_field);
        datePicker = findViewById(R.id.new_task_date_picker);
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

                //checking for empty task name
                if (taskName.getText().toString().matches("")) {
                    Toast.makeText(this, "You must enter a task name.", Toast.LENGTH_SHORT).show();

                    return true;
                }

                Toast.makeText(NewTaskActivity.this, "Saved!", Toast.LENGTH_SHORT).show();

                //storing saved data in intent
                Intent saveIntent = new Intent(NewTaskActivity.this, TaskListActivity.class);
                saveIntent.putExtra("taskName", taskName.getText().toString());
                saveIntent.putExtra("taskDesc", taskDesc.getText().toString());
                saveIntent.putExtra("month", datePicker.getMonth());
                saveIntent.putExtra("day", datePicker.getDayOfMonth());
                saveIntent.putExtra("year", datePicker.getYear());

                setResult(TaskListActivity.RESULT_NEW, saveIntent);
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
