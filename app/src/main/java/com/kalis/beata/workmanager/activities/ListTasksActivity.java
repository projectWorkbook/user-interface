package com.kalis.beata.workmanager.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.adapters.TaskAdapter;
import com.kalis.beata.workmanager.database.DBHelper;
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
        setListView();

    }

    private void initiateComponents() {
        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString(CalendarActivity.KEY_DATE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(data);
        actionBar.setDisplayHomeAsUpEnabled(true);
        fab3 = (FloatingActionButton)findViewById(R.id.addTaskFab);

    }

    private void setListView(){
        TaskDAO taskDAO = new TaskDAO(this);
        List<Task> tasks = taskDAO.getAllTasks();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);

        final ListView listView = (ListView)findViewById(R.id.taskListView);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) listView.getItemAtPosition(position);
                // Log.e("Task", task.getName());

                Intent i = new Intent(ListTasksActivity.this, NewTaskActivity.class);
                i.putExtra("task", task);
                startActivity(i);
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
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        setListView();

    }

    public void setListeners() {

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTask();
            }
        });
    }

    public void createNewTask() {
        Intent i = new Intent(this, NewTaskActivity.class);
        startActivity(i);
    }
}
