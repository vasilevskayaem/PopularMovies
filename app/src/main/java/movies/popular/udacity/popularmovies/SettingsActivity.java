package movies.popular.udacity.popularmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.net.PortUnreachableException;

import butterknife.BindView;
import butterknife.ButterKnife;
import movies.popular.udacity.popularmovies.network.MoviesApi;
import movies.popular.udacity.popularmovies.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sort_popular)
    Switch popularSwitch;
    @BindView(R.id.sort_rated)
    Switch ratedSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String sortOrder = PreferenceManager.getSortOrder(this);
        if(MoviesApi.SORT_ORDER_POPULAR.equals(sortOrder))
            popularSwitch.setChecked(true);
        else{
            ratedSwitch.setChecked(true);
        }
        popularSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    ratedSwitch.setChecked(false);
                    PreferenceManager.setSortOrder(SettingsActivity.this, MoviesApi.SORT_ORDER_POPULAR);
                }
            }
        });
        ratedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    popularSwitch.setChecked(false);
                    PreferenceManager.setSortOrder(SettingsActivity.this, MoviesApi.SORT_ORDER_RATED);
                }
            }
        });

    }

}
