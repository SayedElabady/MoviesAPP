package com.example.sayed.moviesapp.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.sayed.moviesapp.R;
import com.example.sayed.moviesapp.adapter.MainAdapter;
import com.example.sayed.moviesapp.detail.DetailsActivity;
import com.example.sayed.moviesapp.model.Movie;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String POPULAR_SORT = "popular";
    private static final String TOP_RATED_SORT = "top_rated";
    private static final String FAVOURITE_SORT = "Favourite";

    private static String currentState = POPULAR_SORT;
    MainContract.Presenter presenter;
    ArrayList<Movie> list;
    MainAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        presenter.setView(this);
        gridView = (GridView) findViewById(R.id.grid_view);
        if (savedInstanceState != null)
            list = (ArrayList) savedInstanceState.getSerializable("gridItems");
        else
            list = new ArrayList<>();
        adapter = new MainAdapter(this, list, presenter);
        gridView.setAdapter(adapter);
        if(savedInstanceState == null)
        presenter.updateGrid(currentState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentState == FAVOURITE_SORT) {
            presenter.updateGrid(FAVOURITE_SORT);
        }
        if (currentState == POPULAR_SORT) {
            presenter.updateGrid(POPULAR_SORT);
        } else if (currentState == TOP_RATED_SORT) {
            presenter.updateGrid(TOP_RATED_SORT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("gridItems", list);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popular) {
            currentState = POPULAR_SORT;
            presenter.updateGrid(POPULAR_SORT);
        } else if (item.getItemId() == R.id.top_rated) {
            currentState = TOP_RATED_SORT;
            presenter.updateGrid(TOP_RATED_SORT);
        } else if (item.getItemId() == R.id.favourite) {
            currentState = FAVOURITE_SORT;
            presenter.updateGrid(FAVOURITE_SORT);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToDetails(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("MovieAttached", movie);
        startActivity(intent);

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateUI(ArrayList<Movie> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public Boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return (cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting());

    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


}
