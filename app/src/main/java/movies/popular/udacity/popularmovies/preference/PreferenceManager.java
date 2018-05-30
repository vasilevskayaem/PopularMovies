package movies.popular.udacity.popularmovies.preference;

import android.content.Context;
import android.content.SharedPreferences;

import movies.popular.udacity.popularmovies.network.MoviesApi;

/**
 * Created by Kate on 5/29/18.
 */

public class PreferenceManager {
    private static final String MOVIES_APP_PREFS = "movies_prefs";
    private static final String SORT_ORDER = "sort_order";

    public static String getSortOrder(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(MOVIES_APP_PREFS,
                Context.MODE_PRIVATE);
        return sharedPref.getString(SORT_ORDER, MoviesApi.SORT_ORDER_POPULAR);
    }

    public static void setSortOrder(Context context, String order){
        SharedPreferences sharedPref = context.getSharedPreferences(MOVIES_APP_PREFS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SORT_ORDER, order);
        editor.commit();
    }
}
