package mobilestudio.io.core.moviesapp.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobilestudio.io.core.moviesapp.R;
import mobilestudio.io.core.moviesapp.model.Movie;

public class DetailsActivity extends AppCompatActivity {
    Movie movieToDisplay;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.average_rate)
    TextView averageRate;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        movieToDisplay = (Movie) getIntent().getExtras().get("MovieAttached");
        Picasso.with(this).load(movieToDisplay.getPosterUrl()).into(poster);
        releaseDate.setText(movieToDisplay.getRelaseDate());
        averageRate.setText(movieToDisplay.getVoteaverage());
        overview.setText(movieToDisplay.getOverview());
        title.setText(movieToDisplay.getTitle());
    }
}
