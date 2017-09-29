package com.example.sayed.moviesapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sayed on 9/22/2017.
 */

public class DBContract {
    public static final String CONTENT_AUTHORITY = "com.sayed.moviesapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String MOVIE_PATH = "movies";

    public static class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(MOVIE_PATH).build();

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_POSTER_URL = "poster_url";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_AVERAGE_RATE = "average_rate";
        public static final String COLUMN_MOVIE_ID = "id";
    }
}
