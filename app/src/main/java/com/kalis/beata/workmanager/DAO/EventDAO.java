package com.kalis.beata.workmanager.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kalis.beata.workmanager.database.DBHelper;
import com.kalis.beata.workmanager.models.Event;

/**
 * Created by Beata on 2016-05-17.
 */
public class EventDAO {

    public static final String TAG = "EventDAO";

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DBHelper.EV_ID, DBHelper.EV_PLACE, DBHelper.EV_START_DATE, DBHelper.EV_START_TIME, DBHelper.EV_END_DATE,DBHelper.EV_END_TIME, DBHelper.EV_NAME, DBHelper.EV_INFO};

    public EventDAO(Context context){
        this.mContext = context;
        mDbHelper = new DBHelper(context);
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

    public void saveEvent(Event event) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.EV_NAME, event.getName() );
        values.put(DBHelper.EV_START_DATE, event.getStartDate());
        values.put(DBHelper.EV_START_TIME, event.getStartTime());
        values.put(DBHelper.EV_END_DATE, event.getEndDate());
        values.put(DBHelper.EV_END_TIME, event.getEndTime());
        values.put(DBHelper.EV_PLACE, event.getPlace());
        values.put(DBHelper.EV_INFO, event.getInfo());

        if(event.getId()==0)
            mDatabase.insert(DBHelper.TABLE_EVENTS, null, values);
        else
            mDatabase.update(DBHelper.TABLE_EVENTS,values,DBHelper.EV_ID + " = " + event.getId(),null);

    }

    public void deleteEvent(Event event) {
        mDatabase.delete(DBHelper.TABLE_EVENTS, DBHelper.EV_ID
                + " = " + event.getId(), null);

    }

    public List<Event> getAllEvents() {
        List<Event> listEvent = new ArrayList<Event>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Event event = cursorToEvent(cursor);
                listEvent.add(event);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listEvent;
    }

    public List<Event> getEventsInDay(int day, int month, int year) {
        List<Event> listEvent = new ArrayList<Event>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns,
                null, null, null, null, null);

        String date = day+"/"+month+"/"+year;

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                System.out.print("we are in cursor yolooo");
                String eventDate = cursor.getString(cursor.getColumnIndex("start_date"));
                System.out.print(eventDate);

                if(date.equals(eventDate))
                {
                    Event event = cursorToEvent(cursor);
                    listEvent.add(event);
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listEvent;
    }

  //  public List<Event> getbyDate()
    public List<Event> getEventsInDay(Date day) {
        List<Event> listEvent = new ArrayList<Event>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns,
                null, null, null, null, null);

        String date = day.getDay()+"/"+day.getMonth()+"/"+day.getYear();

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String eventDate = cursor.getString(2);

                if(date.equals(eventDate))
                {
                    Event event = cursorToEvent(cursor);
                    listEvent.add(event);
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listEvent;
    }


    public Event getEventById(int id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns,
                DBHelper.EV_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            //Operacja gdy brak zadania o podanym ID
            cursor.moveToFirst();
        }

        return cursorToEvent(cursor);
    }



    protected Event cursorToEvent(Cursor cursor) {
        Event event = new Event();
        event.setId(cursor.getInt(0));
        event.setPlace(cursor.getString(1));
        event.setStartDate(cursor.getString(2));
        event.setStartTime(cursor.getString(3));
        event.setEndDate(cursor.getString(4));
        event.setEndTime(cursor.getString(5));
        event.setName(cursor.getString(6));
        event.setInfo(cursor.getString(7));
        return event;
    }





}
