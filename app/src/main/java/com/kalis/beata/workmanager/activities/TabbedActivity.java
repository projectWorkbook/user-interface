package com.kalis.beata.workmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kalis.beata.workmanager.DAO.EventDAO;
import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.adapters.EventAdapter;
import com.kalis.beata.workmanager.adapters.TaskAdapter;
import com.kalis.beata.workmanager.models.Event;
import com.kalis.beata.workmanager.models.MenuOption;
import com.kalis.beata.workmanager.models.Task;

import java.util.Date;
import java.util.List;

public class TabbedActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static MenuOption menuOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuOption = (MenuOption)getIntent().getSerializableExtra(MainActivity.KEY_TYPE);

        getSupportActionBar().setTitle(menuOption.getName());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
                if(menuOption.getName().equals("Today")) {
                    Snackbar.make(getCurrentFocus(), "This are your todays' tasks and events", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(getCurrentFocus(), "Tasks and events of the next week", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

        }
        return true;
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            ListView listView = (ListView)rootView.findViewById(R.id.listView);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fabAddNew);

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                textView.setText("List of tasks: ");
                TaskDAO taskDAO = new TaskDAO(getContext());
                //TODO: pobrac z bazy danych taski po dacie a nie wszystkie

                List<Task> taskList = taskDAO.getAllTasks();
                TaskAdapter taskAdapter = new TaskAdapter(taskList);
                listView.setAdapter(taskAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createNewTask();
                    }
                });

            }

            else {
                textView.setText("List of events: ");
                //TODO: pobrac z bazy danych eventy po dacie
                List<Event> events;
                EventDAO eventDAO = new EventDAO(getContext());
                if(TabbedActivity.menuOption.equals("Today")) {
                   // events = eventDAO.getEventsInDay(new Date());
                    events = eventDAO.getAllEvents();
                }
                else
                {
                    //TODO: pobrac z bazy eventy z przyszlego tygodnia
                   // events = eventDAO.getEventsInDay(new Date());
                    events = eventDAO.getAllEvents();
                }
                EventAdapter eventAdapter = new EventAdapter(events);
                listView.setAdapter(eventAdapter);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createNewEvent();
                    }
                });

            }

            return rootView;
        }

        public void createNewEvent() {
            Intent i = new Intent(getContext(), NewEventActivity.class);
            startActivity(i);
        }

        public void createNewTask() {
            Intent i = new Intent(getContext(), NewTaskActivity.class);
            startActivity(i);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tasks";
                case 1:
                    return "Events";
            }
            return null;
        }
    }
}
