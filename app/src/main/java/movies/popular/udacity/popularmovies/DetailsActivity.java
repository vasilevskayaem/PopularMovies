package movies.popular.udacity.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import movies.popular.udacity.popularmovies.model.ResultFromJson;
import movies.popular.udacity.popularmovies.network.MoviesApi;

public class DetailsActivity extends AppCompatActivity {
    public static String MOVIE = "movie";
    private ResultFromJson movie;
    @BindView(R.id.title)
    TextView titleText;
    @BindView(R.id.overview)
    TextView overviewText;
    @BindView(R.id.rating)
    TextView ratingText;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.movies_poster)
    ImageView posterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movie = getIntent().getExtras().getParcelable(MOVIE);
        if (movie != null) {
            titleText.setText(movie.getTitle());
            Picasso.with(this)
                    .load(MoviesApi.IMAGE_URL + movie.getPosterPath())
                    .into(posterImage);
            overviewText.setText(movie.getOverview());
            ratingText.setText(movie.getVoteAverage() + "/10");
            releaseDate.setText(movie.getReleaseDate());
        }
    }
}
