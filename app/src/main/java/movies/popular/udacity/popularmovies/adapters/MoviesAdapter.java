package movies.popular.udacity.popularmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movies.popular.udacity.popularmovies.R;
import movies.popular.udacity.popularmovies.model.ResultFromJson;

/**
 * Created by Kate on 5/30/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    private static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    List<ResultFromJson> movies = new ArrayList<>();

    public MoviesAdapter(List<ResultFromJson> movies){
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.imageView.getContext())
                .load(IMAGE_URL + movies.get(position).getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movies_poster)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
