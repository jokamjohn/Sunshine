package com.example.android.sunshine.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DELL on 3/11/2015.
 */

public class WeatherContract {
    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.

    public static final String CONTENT_AUTHORITY = "com.example.android.sunshine.app";
    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.

    public static final Uri BASE_CONTENT_URI =Uri.parse("content:// " + CONTENT_AUTHORITY);
    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.

    public static final String PATH_WEATHER = "weather";
    public static final String PATH_LOCATION ="location";

    public static final class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_LOC_KEY ="location_id";
        public static final String COLUMN_DATETEXT = "date";
        public static final String COLUMN_WEATHER_ID = "weather_id";
        public static final String COLUMN_SHORT_DESC = "short_desc";
        public static final String COLUMN_MIN_TEMP ="min";
        public static final String COLUMN_MAX_TEMP = "max";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_PRESSURE ="pressure";
        public static final String COLUMN_WIND_SPEED = "wind";
        public static final String COLUMN_DEGREES = "degrees";


    }

    public class LocationEntry implements BaseColumns{
        public static final String TABLE_NAME ="location";
        public static final String COLUMN_LOCATION_SETTING ="location_setting";
        public static final String COLUMN_CITY_NAME="city_name";
        public static final String COLUMN_COORD_LAT="coord_lat";
        public static final String COLUMN_COORD_LONG="coord_long";
    }
}
