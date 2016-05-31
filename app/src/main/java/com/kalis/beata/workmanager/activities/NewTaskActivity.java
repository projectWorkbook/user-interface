package com.kalis.beata.workmanager.activities;

import android.annotation.TargetApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.Task;
import com.kalis.beata.workmanager.tools.TimeConverter;

import java.util.Calendar;

@TargetApi(23)
public class NewTaskActivity extends AppCompatActivity {

    private FloatingActionButton fabAddNewTask;
    private EditText name;
    private EditText desc;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Task task;
    private TaskDAO taskDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        setTitle("New task");
        name = (EditText) findViewById(R.id.nameEditText);
        desc = (EditText) findViewById(R.id.descEditText);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker) findViewById(R.id.taskDatePicker);
        task = (Task)getIntent().getSerializableExtra("task");


        if(task != null){
            String time[] = task.getEndTime().split(":");
            String data[] = task.getEndDate().split("/");

            name.setText(task.getName());
            desc.setText(task.getInfo());
            timePicker.setCurrentHour(Integer.parseInt(time[0]));
            timePicker.setCurrentMinute(Integer.parseInt(time[1]));
            datePicker.updateDate(Integer.parseInt(data[2]), Integer.parseInt(data[1]) - 1, Integer.parseInt(data[0]));

        }


        initiateComponents();
        setListeners();
    }

    public void initiateComponents() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabAddNewTask = (FloatingActionButton)findViewById(R.id.fabNewTask);
        taskDAO = new TaskDAO(getApplicationContext());
    }

    private void setListeners() {
        fabAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day+"/"+month+"/"+year;

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String time = TimeConverter.timeInString(hour * 3600000 + minute * 60000);


                if(task == null ) {
                    Task t = new Task(name.getText().toString(), date, time, desc.getText().toString());
                    taskDAO.saveTask(t);
                    Toast.makeText(getApplicationContext(), "Dodano nowe zadanie!", Toast.LENGTH_LONG).show();
                }
                else {
                    task.setEndDate(date);
                    task.setEndTime(time);
                    task.setInfo(desc.getText().toString());
                    task.setName(name.getText().toString());
                    taskDAO.saveTask(task);
                    Toast.makeText(getApplicationContext(), "Edytowano zadanie!", Toast.LENGTH_LONG).show();
                }

                onBackPressed();


            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_settings:
                break;
            case R.id.help:
                    Snackbar.make(getCurrentFocus(), "You can add new task!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            case R.id.delete:
                taskDAO.deleteTask(task);
                Toast.makeText(this, "Task deleted", Toast.LENGTH_LONG).show();

                onBackPressed();
                break;

        }
        return true;
    }
}
