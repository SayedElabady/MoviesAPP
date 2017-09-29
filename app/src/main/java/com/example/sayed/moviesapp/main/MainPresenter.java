package com.example.sayed.moviesapp.main;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.sayed.moviesapp.R;
import com.example.sayed.moviesapp.Utils.NetworkUtils;
import com.example.sayed.moviesapp.data.MovieDBHelper;
import com.example.sayed.moviesapp.model.Movie;

/**
 * Created by Sayed on 9/21/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    MovieDBHelper sqLiteOpenHelper;
    Context context;
    public MainPresenter(Context context){
        this.context = context;
        sqLiteOpenHelper = new MovieDBHelper(context);
    }


    @Override
    public void onGridItemClicked(Movie movie) {
        view.navigateToDetails(movie);
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
            movie.setPosterUrl(NetworkUtils.POSTER_BASE_URL + movieObject.getString("poster_path"));
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
        else if (param.equals("Favourite"))
            view.updateUI(getMoviesFromDB());
        else
            new MovieApiFetch().execute(param);

    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    private ArrayList<Movie> getMoviesFromDB() {
    return sqLiteOpenHelper.getFavouriteMovies(context);

    }

    public class MovieApiFetch extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }


            URL url = NetworkUtils.buildMovieURL(strings[0]);

            try {
                String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(url);


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
