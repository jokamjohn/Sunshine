package com.example.android.sunshine.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by DELL on 3/11/2015.
 */
public class TestProvider extends AndroidTestCase {
    private static final String LOG_TAG = TestProvider.class.getSimpleName();
    //changed to testDeleteDb and also removed the lines below mcontext
    public void testDeleteDb() throws Throwable{
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);

    }

    public void testInsertDb(){
        //test data to determine whether the database works
        String testLocationSetting = "99705";
        String testCityName = "North Pole";
        double testLatitude = 64.7488;
        double testLongitude = -147.353;

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME,testCityName);
        values.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING,testLocationSetting);
        values.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT,testLatitude);
        values.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG,testLongitude);

        //the return type is a long
        //lets insert the row of data into the location table
        Long locationRowId;
        locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME,null,values);
        assertTrue(locationRowId !=-1);
        Log.d(LOG_TAG,"New row id: "+locationRowId);

        //query the database and receive a cursor back
        //but first create an array of location string

        String[] columns = {WeatherContract.LocationEntry._ID, WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, WeatherContract.LocationEntry.COLUMN_COORD_LONG,
                WeatherContract.LocationEntry.COLUMN_COORD_LAT, WeatherContract.LocationEntry.COLUMN_CITY_NAME};

        //query the database

        Cursor cursor = db.query(WeatherContract.LocationEntry.TABLE_NAME,columns,null,null,null,null,null);

        //test if the db is not empty, if not it moves to the first entry
        if (cursor.moveToFirst()) {
            //why do we use int and then convert to string
            int locationIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);
            String location = cursor.getString(locationIndex);
            int nameIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
            String name = cursor.getColumnName(nameIndex);
            int latIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
            double latitude = cursor.getDouble(latIndex);
            int longIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
            double longitude = cursor.getDouble(longIndex);
            //testing with the values entered
            assertEquals(testLocationSetting, location);
            assertEquals(testCityName, name);
            assertEquals(testLatitude, latitude);
            assertEquals(testLongitude, longitude);
        }
            else {
            fail("no values returned");
        }

        //weather entry

        ContentValues weatherContentValues = new ContentValues();
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_LOC_KEY, locationRowId);
        String testDate = "11/03/2015";
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_DATETEXT, testDate);
        Double testDegrees = 1.1;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, testDegrees);
        Double testHumidity = 1.2;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, testHumidity);
        Double testMaxTemp = 20.0;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, testMaxTemp);
        Double testMinTemp = 10.0;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, testMinTemp);
        Double testPressure = 1.3;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, testPressure);
        String testShortDesc = "Asteroids";
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, testShortDesc);
        Double testWindSpeed = 1.5;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, testWindSpeed);
        Integer testWeatherId = 243;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, testWeatherId);

        long weatherRowId = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, weatherContentValues);
        assertTrue(weatherRowId!=-1);

        String[] weatherColumns = {WeatherContract.WeatherEntry.COLUMN_HUMIDITY, WeatherContract.WeatherEntry.COLUMN_DATETEXT, WeatherContract.WeatherEntry.COLUMN_LOC_KEY,
                WeatherContract.WeatherEntry.COLUMN_DEGREES, WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, WeatherContract.WeatherEntry.COLUMN_PRESSURE,
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, WeatherContract.WeatherEntry.COLUMN_PRESSURE, WeatherContract.WeatherEntry.COLUMN_WIND_SPEED};

        Cursor weatherCursor = db.query(WeatherContract.WeatherEntry.TABLE_NAME, weatherColumns, null, null, null, null, null);
        if(weatherCursor.moveToFirst()){
            int maxIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
            Double maxTemp = weatherCursor.getDouble(maxIndex);
            int minIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
            int pressureIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
            int windIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
            int weatherIdIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
            int dateIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATETEXT);
            int degreesIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DEGREES);
            int shortDescIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
            int humidityIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
            int locIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
            assertEquals(maxTemp, testMaxTemp);
            Double minTemp = weatherCursor.getDouble(minIndex);
            assertEquals(minTemp, testMinTemp);
            Double pressure = weatherCursor.getDouble(pressureIndex);
            assertEquals(pressure, testPressure);
            Double windSpeed = weatherCursor.getDouble(windIndex);
            assertEquals(windSpeed, testWindSpeed);
            Double humidity = weatherCursor.getDouble(humidityIndex);
            assertEquals(humidity, testHumidity);
            Double degrees = weatherCursor.getDouble(degreesIndex);
            assertEquals(degrees, testDegrees);
            String date= weatherCursor.getString(dateIndex);
            assertEquals(date, testDate);
            Integer weatherId = weatherCursor.getInt(weatherIdIndex);
            assertEquals(weatherId, testWeatherId);
            long locationId = weatherCursor.getLong(locIndex);
            assertEquals(locationId,(double)locationRowId);

        }
        else {
            fail("No weather values returned!");
        }






        }





    }


