package com.kalis.beata.workmanager.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kalis.beata.workmanager.R;

public class NewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        setTitle("New event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: create sth similar to NewTaskActivity but with different fields (need structure of database)
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
                Snackbar.make(getCurrentFocus(), "You can add new event!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
        return true;
    }


}
