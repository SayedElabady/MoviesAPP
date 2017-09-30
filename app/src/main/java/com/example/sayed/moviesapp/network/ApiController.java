package com.example.sayed.moviesapp.network;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import com.example.sayed.moviesapp.model.Review;
import com.example.sayed.moviesapp.model.Trailer;



public class ApiController {
    private static final String API_KEY = "f21cbdbeb6002cb0247cb93d8865f28a";

    public static Observable<List<Review>> getMovieReview(String id) {
        return ApiClient.getClient()
                .create(MovieApiInterface.class)
                .getMovieReview(id, API_KEY)
                .map(Review.ReviewResponse::getResults)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());

    }

    public static Observable<List<Trailer>> getMovieTrailer(String id) {
        return ApiClient.getClient()
                .create(MovieApiInterface.class)
                .getMovieTrailer(id, API_KEY)
                .map(Trailer.TrailerResponse::getTrailers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());

    }
}
