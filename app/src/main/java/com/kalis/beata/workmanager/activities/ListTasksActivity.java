package com.kalis.beata.workmanager.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.adapters.TaskAdapter;
import com.kalis.beata.workmanager.models.Task;

import java.util.ArrayList;
import java.util.List;

public class ListTasksActivity extends AppCompatActivity {

    private FloatingActionButton fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

        initiateComponents();
        setListeners();
    }

    public void initiateComponents() {
        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString(CalendarActivity.KEY_DATE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(data);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //TODO: get list of tasks from database by date
     //   TaskAdapter taskAdapter = new TaskAdapter();

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("nauka" , "12-03-2013"));
        tasks.add(new Task("sen" , "15-03-2013"));
        fab3 = (FloatingActionButton)findViewById(R.id.addTaskFab);
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        ListView listView = (ListView)findViewById(R.id.taskListView);
        listView.setAdapter(taskAdapter);

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
        }
        return true;
    }

    public void setListeners() {

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add new task to database
            }
        });
    }
}
