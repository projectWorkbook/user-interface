package com.kalis.beata.workmanager.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kalis.beata.workmanager.DAO.TaskDAO;
import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.Task;
import com.kalis.beata.workmanager.tools.TimeConverter;

import android.os.Handler;

public class TaskWorktimeActivity extends AppCompatActivity {

    private Task task;
    private Toolbar toolbar;

    private Button countdownManager;
    private Button stopWorking;
    private Button finishWorkingButton;

    private TextView taskName;
    private TextView taskInfo;
    private TextView taskTime;
    private long remainingTime;

    private MyCountDownTimer timer;
    private Handler handler = new Handler();
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_worktime);

        initializeComponents();
        setListeners();

    }

    private void initializeComponents(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        countdownManager = (Button) findViewById(R.id.CountdownButton);
        stopWorking = (Button) findViewById(R.id.endWorkButton);
        finishWorkingButton = (Button) findViewById(R.id.finishWorkButton);

        taskName = (TextView) findViewById(R.id.WorktimeTaskName);
        taskInfo = (TextView) findViewById(R.id.WorktimeTaskInfo);
        taskTime = (TextView) findViewById(R.id.WorktimeTaskTimer);

        task = (Task)getIntent().getSerializableExtra("task");
        time = task.getEndTime();
        remainingTime = TimeConverter.timeInMillis(time);

        taskName.setText(task.getName());
        taskInfo.setText(task.getInfo());
        taskTime.setText(time);

        setTitle(task.getEndDate());

    }

    private void setListeners(){
        countdownManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(count, 0);
            }
        });

        stopWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        finishWorkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskTime.setText("00:00:00");
                task.setState(1);
                onBackPressed();
            }
        });
    }

    private Runnable count = new Runnable() {
        @Override
        public void run() {
            if (countdownManager.getText().equals("Start")) {

                timer = new MyCountDownTimer(remainingTime, 500);
                timer.start();
                countdownManager.setText("Pause");


            } else if (countdownManager.getText().equals("Pause")) {

                timer.cancel();
                countdownManager.setText("Start");
            }
        }
    };


    public class MyCountDownTimer extends CountDownTimer
    {

        public MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onFinish()
        {
            taskTime.setText("00:00:00");
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            remainingTime = millisUntilFinished;
            taskTime.setText(TimeConverter.timeInString(millisUntilFinished));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        if(countdownManager.getText().equals("Pause")){
            timer.cancel();
        }
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        task.setEndTime(taskTime.getText().toString());
        taskDAO.saveTask(task);
        super.onBackPressed();
    }
}
