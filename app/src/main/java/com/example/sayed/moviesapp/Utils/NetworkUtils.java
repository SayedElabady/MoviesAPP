package com.example.sayed.moviesapp.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Sayed on 9/22/2017.
 */

public class NetworkUtils {
    public final static String API_KEY = "f21cbdbeb6002cb0247cb93d8865f28a";
    public final static String BASE_API_URL = "http://api.themoviedb.org/3/movie/";
    public final static String PARAM_API = "api_key";
    public final static String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500/";
    public static URL buildMovieURL(String sortType) {
        Uri builtUri = Uri.parse(BASE_API_URL + sortType).buildUpon()
                .appendQueryParameter(PARAM_API, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

     //   Log.v("MainPres", "Built URI " + url);

        return url;
    }

   public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildTrailerURL(String string) {
        Uri builtUri = Uri.parse(BASE_API_URL + string + "/reviews" ).buildUpon()
                .appendQueryParameter(PARAM_API, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //   Log.v("MainPres", "Built URI " + url);

        return url;
    }

    public static URL buildReviewURL(String string) {
        Uri builtUri = Uri.parse(BASE_API_URL + string + "/videos" ).buildUpon()
                .appendQueryParameter(PARAM_API, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //   Log.v("MainPres", "Built URI " + url);

        return url;
    }
}
