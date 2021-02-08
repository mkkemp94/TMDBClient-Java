package com.androidtutz.anushka.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.databinding.ActivityMovieBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MovieActivity extends AppCompatActivity
{
    private ActivityMovieBinding binding;
    private Movie movie;
    
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
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }
}
