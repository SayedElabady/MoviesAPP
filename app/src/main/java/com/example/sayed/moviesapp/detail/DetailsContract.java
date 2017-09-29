package com.example.sayed.moviesapp.detail;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import com.example.sayed.moviesapp.model.Movie;
import com.example.sayed.moviesapp.model.Review;
import com.example.sayed.moviesapp.model.Trailer;

/**
 * Created by Sayed on 9/22/2017.
 */

public interface DetailsContract {
    interface View {


        void updateReviews(List<Review> list);

        void updateTrailers(List<Trailer> list);

        void updateDetails(Movie movie);


    }

    interface Presenter {

        void fetch(Movie movie);

        boolean isFavourited(String movieID);

        void favouriteIsClicked(Movie movie);

        void setView(@NonNull View view);

    }
}
