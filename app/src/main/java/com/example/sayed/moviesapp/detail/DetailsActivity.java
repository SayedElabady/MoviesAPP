package com.example.sayed.moviesapp.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sayed.moviesapp.R;
import com.example.sayed.moviesapp.model.Movie;
import com.example.sayed.moviesapp.model.Review;
import com.example.sayed.moviesapp.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View, TrailerListener {
    Movie movie;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.average_rate)
    TextView averageRate;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.reviews)
    RecyclerView reviews;
    @BindView(R.id.trailers)
    RecyclerView trailers;
    List<Review> reviewsList;
    DetailsContract.Presenter presenter;
    ReviewAdapter reviewAdapter;
    TrailerAdapter trailerAdapter;
    List<Trailer> trailerList;
    @BindView(R.id.fav_button)
    ImageView favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        reviewsList = new ArrayList<>();
        trailerList = new ArrayList<>();
        presenter = new DetailsPresenter(this);
        presenter.setView(this);
        reviewAdapter = new ReviewAdapter(reviewsList);
        reviews.setLayoutManager(new LinearLayoutManager(this));
        reviews.setAdapter(reviewAdapter);
        trailerAdapter = new TrailerAdapter(trailerList, this);
        trailers.setLayoutManager(new LinearLayoutManager(this));
        trailers.setAdapter(trailerAdapter);
        movie = (Movie) getIntent().getExtras().get("MovieAttached");
        presenter.fetch(movie);
    }


    @Override
    public void updateReviews(List<Review> list) {
        reviewsList.clear();
        reviewsList.addAll(list);
        reviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTrailers(List<Trailer> list) {
        trailerList.clear();
        trailerList.addAll(list);
        trailerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateDetails(Movie movieToDisplay) {

        Picasso.with(this).load(movieToDisplay.getPosterUrl()).into(poster);
        releaseDate.setText(movieToDisplay.getRelaseDate());
        averageRate.setText(movieToDisplay.getVoteaverage());
        title.setText(movieToDisplay.getTitle());
        if (presenter.isFavourited(movieToDisplay.getId())) {
            favButton.setImageResource(R.drawable.ic_star_black_24dp);
        }else
            favButton.setImageResource(R.drawable.ic_star_border_black_24dp);

    }

    @OnClick(R.id.fav_button)
    void click() {
        if (presenter.isFavourited(movie.getId()))
            favButton.setImageResource(R.drawable.ic_star_border_black_24dp);
        else
            favButton.setImageResource(R.drawable.ic_star_black_24dp);

        presenter.favouriteIsClicked(movie);
    }

    @Override
    public void onTrailerClicked(String idStr) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.youtube_base_url) + idStr));
        startActivity(intent);
    }
}
