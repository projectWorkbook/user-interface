package com.kalis.beata.workmanager.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.kalis.beata.workmanager.DAO.EventDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.adapters.EventAdapter;
import com.kalis.beata.workmanager.models.Event;

import java.util.Date;
import java.util.List;

public class ListEventsActivity extends AppCompatActivity {

    private FloatingActionButton fabNewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);

        initiateComponents();
        setListeners();
    }

    public void initiateComponents() {
        Bundle bundle = getIntent().getExtras();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(bundle.getString(CalendarActivity.KEY_DATE));
        actionBar.setDisplayHomeAsUpEnabled(true);

        EventDAO eventDAO = new EventDAO(this);
        //TODO: pobrac z bazy eventy po dacie
        List<Event> events = eventDAO.getEventsInDay(new Date());
        EventAdapter eventAdapter = new EventAdapter(events);
        ListView listView = (ListView)findViewById(R.id.eventListView);
        listView.setAdapter(eventAdapter);

        fabNewEvent = (FloatingActionButton)findViewById(R.id.eventAddFab);


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

        fabNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add new event to database
                createNewEvent();
            }
        });
    }

    public void createNewEvent() {
        Intent i = new Intent(this, NewEventActivity.class);
        startActivity(i);
    }
}
