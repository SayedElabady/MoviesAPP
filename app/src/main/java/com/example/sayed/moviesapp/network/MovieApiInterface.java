package com.example.sayed.moviesapp.network;

import io.reactivex.Observable;
import com.example.sayed.moviesapp.model.Review;
import com.example.sayed.moviesapp.model.Trailer;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sayed on 9/23/2017.
 */

public interface MovieApiInterface {

    @GET("movie/{id}/reviews")
    Observable<Review.ReviewResponse> getMovieReview(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Observable<Trailer.TrailerResponse> getMovieTrailer(@Path("id") String id, @Query("api_key") String apiKey);

}
