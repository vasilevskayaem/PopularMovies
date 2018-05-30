package movies.popular.udacity.popularmovies.network;

import movies.popular.udacity.popularmovies.model.ResultsFromJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Kate on 5/29/18.
 */

public interface MoviesApi {
    public String API_KEY = "API_KEY";
    public String SORT_ORDER_POPULAR = "popular";
    public String SORT_ORDER_RATED = "top_rated";

    @GET("/3/movie/{sort}")
    Call<ResultsFromJson> getMovies(@Path("sort") String sortOrder,
                                    @Query("api_key") String apiKey);

}
