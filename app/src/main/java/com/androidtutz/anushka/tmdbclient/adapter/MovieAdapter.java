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

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/10/2018.
 */
public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>
{
    private Context context;
    
    public MovieAdapter(Context context)
    {
        super(Movie.CALLBACK);
        this.context = context;
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
        Movie movie = getItem(position);
        holder.binding.setMovie(movie);
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
                    Movie selectedMovie = getItem(position);
        
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", selectedMovie);
                    context.startActivity(intent);
                }
            }
        }
    }
}
