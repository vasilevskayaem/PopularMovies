package movies.popular.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import movies.popular.udacity.popularmovies.model.ResultsFromJson;
import movies.popular.udacity.popularmovies.network.MoviesApi;
import movies.popular.udacity.popularmovies.network.MoviesNetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MoviesApi moviesApi = MoviesNetworkController.getApi();
        moviesApi.getMovies("popular", MoviesApi.API_KEY, 1).enqueue(new Callback<ResultsFromJson>() {
            @Override
            public void onResponse(Call<ResultsFromJson> call, Response<ResultsFromJson> response) {
                Toast.makeText(MainActivity.this,response.body().getTotalPages()+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResultsFromJson> call, Throwable t) {

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
