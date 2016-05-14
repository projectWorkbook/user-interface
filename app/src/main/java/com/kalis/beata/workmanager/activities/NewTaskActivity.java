package com.kalis.beata.workmanager.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kalis.beata.workmanager.R;

public class NewTaskActivity extends AppCompatActivity {

    private ImageButton addNewTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        setTitle("New task");

        initiateComponents();
        setListeners();
    }

    public void initiateComponents() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       addNewTask = (ImageButton)findViewById(R.id.taskImageButton);
    }

    public void setListeners() {
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: dodac nowe zadanie do bazy danych
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
}
