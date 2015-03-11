package com.example.android.sunshine.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 3/11/2015.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP DATABASE IF EXISTS"+ WeatherContract.WeatherEntry.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Location table creation
        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE" + WeatherContract.LocationEntry.TABLE_NAME+"("+
                WeatherContract.LocationEntry._ID+"INTEGER_PRIMARY_KEY"+
                WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING +"TEXT UNIQUE NOT NULL"+
                WeatherContract.LocationEntry.COLUMN_CITY_NAME+"REAL NOT NULL"+
                WeatherContract.LocationEntry.COLUMN_COORD_LAT+"REAL NOT NULL"+
                WeatherContract.LocationEntry.COLUMN_COORD_LONG+"REAL NOT NULL"+
                " );";
        //weather table creation
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE" + WeatherContract.WeatherEntry.TABLE_NAME+ "("+
                WeatherContract.WeatherEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                WeatherContract.WeatherEntry.COLUMN_LOC_KEY + "INTEGER NOT NULL" +
                WeatherContract.WeatherEntry.COLUMN_DATETEXT + "INTEGER NOT NULL" +
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC+"TEXT NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_WEATHER_ID+"INTEGER NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_MIN_TEMP+"REAL NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_MAX_TEMP+"REAL NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_HUMIDITY+"REAL NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_PRESSURE+"REAL NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_WIND_SPEED+"REAL NOT NULL"+
                WeatherContract.WeatherEntry.COLUMN_DEGREES+"REAL NOT NULL"+

                "FOREIGN KEY ("+ WeatherContract.WeatherEntry.COLUMN_LOC_KEY+")REFERENCES"+
                WeatherContract.LocationEntry.TABLE_NAME+"("+ WeatherContract.LocationEntry._ID+"),"+
                "UNIQUE("+ WeatherContract.WeatherEntry.COLUMN_DATETEXT+","+ WeatherContract.WeatherEntry.COLUMN_LOC_KEY+")ON CONFLICT REPLACE);";

        db.execSQL(SQL_CREATE_WEATHER_TABLE);


    }
}
