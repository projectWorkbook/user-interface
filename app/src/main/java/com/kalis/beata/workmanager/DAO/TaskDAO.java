package com.kalis.beata.workmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kalis.beata.workmanager.database.DBHelper;
import com.kalis.beata.workmanager.models.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beata on 2016-05-17.
 */
public class TaskDAO {

    public static final String TAG = "TaskDAO";

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DBHelper.TS_ID, DBHelper.TS_END_DATE, DBHelper.TS_END_TIME, DBHelper.TS_PROGRESS, DBHelper.TS_NAME, DBHelper.TS_STATE, DBHelper.TS_DURATION, DBHelper.TS_INFO};

    public TaskDAO(Context context){
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        //open database
        try{
            open();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void  open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public void saveTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TS_NAME, task.getName());
        values.put(DBHelper.TS_END_DATE, task.getEndDate());
        values.put(DBHelper.TS_END_TIME, task.getEndTime());
        values.put(DBHelper.TS_INFO, task.getInfo());
        values.put(DBHelper.TS_DURATION, String.valueOf(task.getDuration()));
        values.put(DBHelper.TS_PROGRESS, task.getProgress());
        values.put(DBHelper.TS_STATE, task.getState());

        if(task.getId()==0)
        {
            task.setId(mDatabase.insert(DBHelper.TABLE_TASKS, null, values));
        }

        else
        {
            mDatabase.update(DBHelper.TABLE_TASKS,values,DBHelper.TS_ID + " = " + task.getId(),null);
        }


    }

    public void deleteTask(Task task) {
        mDatabase.delete(DBHelper.TABLE_TASKS, DBHelper.TS_ID
                + " = " + task.getId(), null);

    }

    public List<Task> getAllTasks() {
        List<Task> listTask = new ArrayList<Task>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_TASKS, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = cursorToTask(cursor);
                listTask.add(task);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listTask;
    }

    public List<Task> tasksByDate(String date){
        List<Task> listTask = new ArrayList<Task>();
        String []columns = {DBHelper.EV_END_DATE};
        Cursor cursor = mDatabase.query(DBHelper.TABLE_TASKS, null, DBHelper.TS_END_DATE
        + " = ?", new String[] {date}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = cursorToTask(cursor);
                listTask.add(task);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listTask;
    }

    public Task getTaskById(int id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_TASKS, mAllColumns,
                DBHelper.TS_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            //Operacja gdy brak zadania o podanym ID
            cursor.moveToFirst();
        }

        return cursorToTask(cursor);
    }



    protected Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.TS_ID)));
        task.setEndDate(cursor.getString(cursor.getColumnIndex(DBHelper.TS_END_DATE)));
        task.setEndTime(cursor.getString(cursor.getColumnIndex(DBHelper.TS_END_TIME)));
        task.setProgress(cursor.getInt(cursor.getColumnIndex(DBHelper.TS_PROGRESS)));
        task.setName(cursor.getString(cursor.getColumnIndex(DBHelper.TS_NAME)));
        task.setState(cursor.getInt(cursor.getColumnIndex(DBHelper.TS_STATE)));
        task.setDuration(cursor.getFloat(cursor.getColumnIndex(DBHelper.TS_DURATION)));
        task.setInfo(cursor.getString(cursor.getColumnIndex(DBHelper.TS_INFO)));
        return task;
    }




}
