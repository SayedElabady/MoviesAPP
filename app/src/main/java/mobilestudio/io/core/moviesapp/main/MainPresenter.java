package mobilestudio.io.core.moviesapp.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mobilestudio.io.core.moviesapp.model.Movie;

/**
 * Created by Sayed on 9/21/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    private final String API_KEY = "%%%%";
    private final String BASE_API_URL = "http://api.themoviedb.org/3/movie/";
    private final String PARAM_API = "api_key";
    private final static String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500/";


    @Override
    public void onGridItemClicked(Movie movie) {
        view.navigateToDetails(movie);
    }

    private URL buildURL(String sortTyp) {

        Uri builtUri = Uri.parse(BASE_API_URL + sortTyp).buildUpon()
                .appendQueryParameter(PARAM_API, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("MainPres", "Built URI " + url);

        return url;
    }

    private String getResponseFromHttpUrl(URL url) throws IOException {
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

    private ArrayList<Movie> getMoviesFromJson(String jsonStr) throws JSONException {
        ArrayList<Movie> list = new ArrayList<>();
        JSONObject totalRes = new JSONObject(jsonStr);
        JSONArray result = totalRes.getJSONArray("results");
        for (int i = 0; i < result.length(); ++i) {
            JSONObject movieObject = result.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(movieObject.getString("id"));
            movie.setTitle(movieObject.getString("title"));
            movie.setPosterUrl(POSTER_BASE_URL + movieObject.getString("poster_path"));
            movie.setOverview(movieObject.getString("overview"));
            movie.setVoteaverage(movieObject.getString("vote_average"));
            movie.setRelaseDate(movieObject.getString("release_date"));
            list.add(movie);

        }
        return list;
    }

    @Override
    public void updateGrid(String param) {
        view.setPresenter(this);
        if (!(view.isConnected()))
            view.displayErrorMessage("Please enable your Internet Connection");
        else
            new MovieApiFetch().execute(param);

    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    public class MovieApiFetch extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }


            URL url = buildURL(strings[0]);

            try {
                String responseFromHttpUrl = getResponseFromHttpUrl(url);


                return getMoviesFromJson(responseFromHttpUrl);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            view.updateUI(movies);

        }
    }
}
