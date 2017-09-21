package mobilestudio.io.core.moviesapp.main;

import java.util.ArrayList;

import mobilestudio.io.core.moviesapp.model.Movie;

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

        void setView(View view);

    }

}
