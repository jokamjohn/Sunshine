package com.example.android.sunshine.app;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * Created by DELL on 3/11/2015.
 */
public class TestDb extends AndroidTestCase {
    public void testCreateDb() throws Throwable{
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext).getReadableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

}
