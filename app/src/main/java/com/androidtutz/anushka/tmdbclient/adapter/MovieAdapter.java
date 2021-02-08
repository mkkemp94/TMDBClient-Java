package com.androidtutz.anushka.tmdbclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.databinding.MovieListItemBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.view.MovieActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

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
        Movie movie = movieArrayList.get(position);
        holder.binding.setMovie(movie);
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
            binding.setClickHandlers(new MovieAdapterClickHandlers());
        }
    
        public class MovieAdapterClickHandlers
        {
            public void movieClicked(View view)
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
        }
    }
}
