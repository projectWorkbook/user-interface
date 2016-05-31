package com.kalis.beata.workmanager.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.kalis.beata.workmanager.R;

public class CalendarActivity extends AppCompatActivity {

    public final static String KEY_DATE = "data";
    private String tableName;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initiateComponents();
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
                Snackbar.make(getCurrentFocus(), "Choose a day!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
        return true;
    }

    public void initiateComponents() {

        bundle = getIntent().getExtras();
        tableName = bundle.getString(MainActivity.KEY_TYPE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(tableName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String data = dayOfMonth + "-" + (month + 1) + "-" + year;
                openListView(data);
            }
        });

    }

    public void openListView(String data) {

        if(tableName.equals("Tasks")) {
            Intent i = new Intent(this, ListTasksActivity.class);
            i.putExtra(KEY_DATE, data);
            startActivity(i);

        }
        else {
            Intent i = new Intent(this, ListEventsActivity.class);
            i.putExtra(KEY_DATE, data);
            startActivity(i);

        }
        }


}
