package com.kalis.beata.workmanager.database;

/**
 * Created by Beata on 2016-05-17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Planner.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "events_table";
    public static final String EV_ID = "id";
    public static final String EV_PLACE = "event_place";
    public static final String EV_START_DATE = "start_date";
    public static final String EV_START_TIME = "start_time";
    public static final String EV_END_DATE = "end_date";
    public static final String EV_END_TIME = "end_time";
    public static final String EV_NAME = "event_name";
    public static final String EV_INFO = "event_info";

    public static final String TABLE_TASKS = "tasks_table";
    public static final String TS_ID = "id";
    public static final String TS_END_DATE = "end_date";
    public static final String TS_END_TIME = "end_time";
    public static final String TS_PROGRESS  = "progress";
    public static final String TS_NAME = "task_name";
    public static final String TS_STATE = "task_state";
    public static final String TS_INFO = "task_info";
    public static final String TS_DURATION = "duration";

    //edycja tasku i eventów



    private static final String SQL_CREATE_TABLE_EVENTS =
            "CREATE TABLE " + TABLE_EVENTS + "("
                    + EV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EV_PLACE + " TEXT, "
                    + EV_START_DATE + " TEXT, "
                    + EV_START_TIME + " TEXT, "
                    + EV_END_DATE + " TEXT, "
                    + EV_END_TIME + " TEXT, "
                    + EV_NAME + " TEXT, "
                    + EV_INFO + " TEXT)";

    private static final String SQL_CREATE_TABLE_TASKS =
            "CREATE TABLE " + TABLE_TASKS + "("
                    + TS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TS_END_DATE + " TEXT, "
                    + TS_END_TIME + " TEXT, "
                    + TS_PROGRESS + " INTEGER, "
                    + TS_NAME + " TEXT, "
                    + TS_STATE + " INTEGER, "
                    + TS_DURATION + " REAL, "
                    + TS_INFO + " TEXT)";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_EVENTS);
        db.execSQL(SQL_CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }


}
