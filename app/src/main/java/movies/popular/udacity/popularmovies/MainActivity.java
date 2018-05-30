package movies.popular.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import movies.popular.udacity.popularmovies.adapters.MoviesAdapter;
import movies.popular.udacity.popularmovies.model.ResultsFromJson;
import movies.popular.udacity.popularmovies.network.MoviesApi;
import movies.popular.udacity.popularmovies.network.MoviesNetworkController;
import movies.popular.udacity.popularmovies.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String sortOrder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sortOrder = PreferenceManager.getSortOrder(this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        moviesList.setLayoutManager(mLayoutManager);
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // if return from other activity but sort order was changed
        String actualSortOrder = PreferenceManager.getSortOrder(this);
        if(!actualSortOrder.equals(sortOrder)) {
            sortOrder = actualSortOrder;
            updateUI();
        }
    }

    private void updateUI(){
        MoviesApi moviesApi = MoviesNetworkController.getApi();
        moviesApi.getMovies(sortOrder, MoviesApi.API_KEY).enqueue(new Callback<ResultsFromJson>() {
            @Override
            public void onResponse(Call<ResultsFromJson> call, Response<ResultsFromJson> response) {
                MoviesAdapter moviesAdapter = new MoviesAdapter(response.body().getResults());
                moviesList.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<ResultsFromJson> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
