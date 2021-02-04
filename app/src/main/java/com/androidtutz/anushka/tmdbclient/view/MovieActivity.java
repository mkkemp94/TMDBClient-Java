package com.androidtutz.anushka.tmdbclient.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.databinding.ActivityMovieBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity
{
    private ActivityMovieBinding binding;
    
    private Movie movie;
    
    private String image;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        
        setSupportActionBar(binding.toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        Intent intent = getIntent();
        
        if ( intent.hasExtra("movie") )
        {
            movie = getIntent().getParcelableExtra("movie");
            binding.setMovie(movie);
            
            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();
            
            image = movie.getPosterPath();
            
            String path = "https://image.tmdb.org/t/p/w500" + image;
            
            Glide.with(this)
                    .load(path)
                    .placeholder(R.drawable.loading)
                    .into(binding.ivMovieLarge);
            
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }
}
