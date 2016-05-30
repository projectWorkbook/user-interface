package com.kalis.beata.workmanager.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.Task;

public class NewTaskActivity extends AppCompatActivity {

    private FloatingActionButton fabAddNewTask;
    EditText name;
    EditText desc;
    RatingBar ratingBar;
    DatePicker datePicker;

    private FloatingActionButton fabAddNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        setTitle("New task");

        name = (EditText) findViewById(R.id.nameEditText);
        desc = (EditText) findViewById(R.id.descEditText);
        ratingBar = (RatingBar) findViewById(R.id.taskRatingBar);
        datePicker = (DatePicker) findViewById(R.id.taskDatePicker);
        Task task = (Task)getIntent().getSerializableExtra("task");
        if(task != null){
            name.setText(task.getName());
            desc.setText(task.getInfo());
        }


        initiateComponents();
        setListeners();
    }

    public void initiateComponents() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabAddNewTask = (FloatingActionButton)findViewById(R.id.fabNewTask);
    }

    private void setListeners() {
        fabAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day+"/"+month+"/"+year;

                TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                Task t = new Task(name.getText().toString(), ratingBar.getRating(), date, desc.getText().toString());
                taskDAO.saveTask(t);


                Toast.makeText(getApplicationContext(), "Dodano nowe zadanie!", Toast.LENGTH_LONG).show();
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

        }
        return true;
    }
}
