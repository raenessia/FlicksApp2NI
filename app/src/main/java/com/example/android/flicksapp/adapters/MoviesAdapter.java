package com.example.android.flicksapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.flicksapp.DetailActivity;
import com.example.android.flicksapp.R;
import com.example.android.flicksapp.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("pls", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("pls", "onBindViewHolder: " + position);
        Movie movie = movies.get(position);

        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.poster_iv);
            tvTitle = itemView.findViewById(R.id.title_tv);
            tvOverview = itemView.findViewById(R.id.overview_tv);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl = movie.getPosterPath();
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Access detail activity
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });
        }
    }
}
