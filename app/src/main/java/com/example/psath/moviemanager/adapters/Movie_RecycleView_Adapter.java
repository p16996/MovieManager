package com.example.psath.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psath.moviemanager.Models.Movie;
import com.example.psath.moviemanager.R;
import com.example.psath.moviemanager.activities.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by psath on 9/13/2017.
 */

public class Movie_RecycleView_Adapter extends RecyclerView.Adapter<Movie_RecycleView_Adapter.ViewHolder> {
    List<Movie> movies;
    Context context;

    public Movie_RecycleView_Adapter(Context context,List<Movie> movies){
        this.movies=movies;
        this.context=context;

    }

    private Context getContext(){
        return context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie=movies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieOverview.setText(movie.getOverview());
        String movie_rating= String.valueOf(movie.getVoteAverage());
        holder.movieRating.setText(movie_rating);
        Picasso.with(getContext()).load(movie.getPosterPath()).resize(300,500).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movies.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movieImage)
        ImageView movieImage;
        @BindView(R.id.movieTitle)
        TextView movieTitle;
        @BindView(R.id.movieOverview)
        TextView movieOverview;
        @BindView(R.id.cvMovieItem)
        CardView cvMovieItem;
        @BindView(R.id.movieRating)
        TextView movieRating;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie=movies.get(getAdapterPosition());
            Intent intent=new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("movie",movie);
            getContext().startActivity(intent);


        }
    }
}
