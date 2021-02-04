package com.androidtutz.anushka.tmdbclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.databinding.MovieListItemBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.view.MovieActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/10/2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private Context context;
    private ArrayList<Movie> movieArrayList;
    
    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList)
    {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }
    
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        MovieListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false
        );
        
        return new MovieViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        holder.binding.tvTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.binding.tvRating.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));
        
        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();
        
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.binding.ivMovie);
    }
    
    @Override
    public int getItemCount()
    {
        return movieArrayList.size();
    }
    
    
    public class MovieViewHolder extends RecyclerView.ViewHolder
    {
        private final MovieListItemBinding binding;
    
        public MovieViewHolder(MovieListItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            
            binding.getRoot().setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int position = getAdapterPosition();
                    
                    if ( position != RecyclerView.NO_POSITION )
                    {
                        Movie selctedMovie = movieArrayList.get(position);
                        
                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selctedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
