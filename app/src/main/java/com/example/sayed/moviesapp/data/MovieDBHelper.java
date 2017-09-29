package com.example.sayed.moviesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.sayed.moviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sayed on 9/29/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "movies.db";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String createMoviesTable = "CREATE TABLE " + DBContract.MovieEntry.TABLE_NAME + " ( "
                + DBContract.MovieEntry._ID + " INTEGER PRIMARY KEY, "
                + DBContract.MovieEntry.COLUMN_MOVIE_ID + " TEXT, "
                + DBContract.MovieEntry.COLUMN_TITLE + " TEXT, "
                + DBContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT, "
                + DBContract.MovieEntry.COLUMN_AVERAGE_RATE + " TEXT, "
                + DBContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, "
                + DBContract.MovieEntry.COLUMN_POSTER_URL + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(createMoviesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Movie> getFavouriteMovies(Context context) {
        ArrayList<Movie> favourites = new ArrayList<>();
        Cursor cursor = context.getContentResolver()
                .query(
                        DBContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
        if (cursor.moveToFirst()) {
            do {
                String movieId = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_MOVIE_ID));
                String title = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_TITLE));
                String overview = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_OVERVIEW));
                String posterPath = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_POSTER_URL));
                String releaseDate = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_RELEASE_DATE));
                String voteAverage = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_AVERAGE_RATE));

                favourites.add(new Movie(movieId, posterPath, releaseDate, overview, voteAverage, title, true));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favourites;
    }

    public boolean isExistinDB(Context context, String movieId) {
        Cursor cursor = context.getContentResolver()
                .query(
                        DBContract.MovieEntry.CONTENT_URI,
                        null,
                        DBContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                        new String[]{movieId},
                        null
                );
        boolean isExist = cursor.moveToFirst();
        cursor.close();
        return isExist;
    }

    public void addToFavourite(Context context, Movie movie) {
        if (!isExistinDB(context, movie.getId())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBContract.MovieEntry.COLUMN_MOVIE_ID, movie.getId());
            contentValues.put(DBContract.MovieEntry.COLUMN_AVERAGE_RATE, movie.getVoteaverage());
            contentValues.put(DBContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
            contentValues.put(DBContract.MovieEntry.COLUMN_POSTER_URL, movie.getPosterUrl());
            contentValues.put(DBContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getRelaseDate());
            contentValues.put(DBContract.MovieEntry.COLUMN_TITLE, movie.getTitle());

            Uri uri = context.getContentResolver().insert(DBContract.MovieEntry.CONTENT_URI, contentValues);
        }
    }
    public void deleteFavourite(Context context , String movieId){
        context.getContentResolver().delete(
                DBContract.MovieEntry.CONTENT_URI,
                DBContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                new String[]{movieId}
        );
    }
}
