package com.example.sayed.moviesapp.detail;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.sayed.moviesapp.data.DBContract;
import com.example.sayed.moviesapp.data.MovieDBHelper;
import com.example.sayed.moviesapp.data.MoviesProvider;
import com.example.sayed.moviesapp.model.Movie;
import com.example.sayed.moviesapp.network.ApiController;

import static java.security.AccessController.getContext;

/**
 * Created by Sayed on 9/22/2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter {
    DetailsContract.View view;
    ContentProvider contentProvider;
    Context context;
    MovieDBHelper movieDBHelper;

    public DetailsPresenter(Context context) {
        this.context = context;
        movieDBHelper = new MovieDBHelper(context);
        contentProvider = new MoviesProvider();
    }

    @Override
    public void fetch(Movie movie) {

        view.updateDetails(movie);
        ApiController
                .getMovieReview(movie.getId())
                .subscribe(
                        reviews -> view.updateReviews(reviews),
                        throwable -> {
                            throwable.printStackTrace();
                        }
                );
        ApiController
                .getMovieTrailer(movie.getId())
                .subscribe(
                        trailers -> view.updateTrailers(trailers)
                );

    }

    @Override
    public boolean isFavourited(String movieID) {
        return movieDBHelper.isExistinDB(context, movieID);
    }

    @Override
    public void favouriteIsClicked(Movie movie) {
        if (isFavourited(movie.getId()))
            movieDBHelper.deleteFavourite(context, movie.getId());
        else
            movieDBHelper.addToFavourite(context, movie);
    }


    @Override
    public void setView(@NonNull DetailsContract.View view) {
        this.view = view;
    }


}
