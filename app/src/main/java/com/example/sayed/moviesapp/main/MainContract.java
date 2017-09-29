package com.example.sayed.moviesapp.main;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.example.sayed.moviesapp.model.Movie;

/**
 * Created by Sayed on 9/21/2017.
 */

public interface MainContract {
    interface View {
        void navigateToDetails(Movie movie);

        void setPresenter(Presenter presenter);

        void updateUI(ArrayList<Movie> list);

        Boolean isConnected();

        void displayErrorMessage(String errorMessage);

    }

    interface Presenter {
        void onGridItemClicked(Movie movie);

        void updateGrid(String param);

        void setView(@NonNull View view);

    }

}
