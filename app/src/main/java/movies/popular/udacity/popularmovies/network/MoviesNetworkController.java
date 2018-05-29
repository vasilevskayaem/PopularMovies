package movies.popular.udacity.popularmovies.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kate on 5/29/18.
 */

public class MoviesNetworkController {
    static final String MOVIES_URL = "http://api.themoviedb.org/";

    private static MoviesApi moviesApi;

    public static MoviesApi getApi() {
        if(moviesApi==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIES_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            moviesApi = retrofit.create(MoviesApi.class);
        }
        return moviesApi;

    }
}
