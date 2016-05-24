package com.kalis.beata.workmanager.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kalis.beata.workmanager.DAO.EventDAO;
import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.adapters.PanelAdapter;
import com.kalis.beata.workmanager.database.DBHelper;
import com.kalis.beata.workmanager.models.Event;
import com.kalis.beata.workmanager.models.MenuOption;
import com.kalis.beata.workmanager.models.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridView gridView;
    private FloatingActionButton fab;
    private FloatingActionButton fab2;
    public final static String KEY_TYPE = "typ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateComponents();
        setListeners();







    }

    public void initiateComponents() {

       // DBHelper dbHelper = new DBHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.yourPanel);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = new Date();
        String startFormat = formatter.format(startDate);

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        endDate.setTime(c.getTime().getTime());
        String endFormat = formatter.format(endDate);

        MenuOption today = new MenuOption("Today", startFormat, startFormat );
        MenuOption nextWeek = new MenuOption("Next week", startFormat, endFormat );

        List<MenuOption> menu = new ArrayList<>();
        menu.add(today);
        menu.add(nextWeek);

        gridView = (GridView)findViewById(R.id.gridView);
        PanelAdapter panelAdapter = new PanelAdapter(menu);
        gridView.setAdapter(panelAdapter);

       /// Event e = new Event("obiad" , "dom", "12/02/2014" , "9:03" , "15/02/2014" , "9:05" , "gotowanie");
      //  EventDAO eventDAO = new EventDAO(this);
      //  eventDAO.saveEvent(e);

    }

    public void setListeners() {
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTask();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEvent();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuOption menuOption = (MenuOption) gridView.getItemAtPosition(position);
                openFragments(menuOption);
            }
        });
    }

    public void createNewTask() {
        Intent i = new Intent(this, NewTaskActivity.class);
        startActivity(i);
    }

    public void createNewEvent() {
        Intent i = new Intent(this, NewEventActivity.class);
        startActivity(i);
    }
    public void openFragments(MenuOption menuOption) {
        Intent i = new Intent(this, TabbedActivity.class);
        i.putExtra(KEY_TYPE, menuOption);
        startActivity(i);
    }

    public void openCalendar(String data) {
        Intent i = new Intent(this, CalendarActivity.class);
        i.putExtra(KEY_TYPE, data);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String name = "";

        if (id == R.id.tasks) {
            name = item.getTitle().toString();
            openCalendar(name);

        } else if (id == R.id.events) {
            name = item.getTitle().toString();
            openCalendar(name);

        } else if (id == R.id.settings) {

        } else if (id == R.id.help) {

        } else if (id == R.id.share) {

        } else if (id == R.id.send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
