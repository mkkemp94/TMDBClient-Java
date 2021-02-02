package com.androidtutz.anushka.tmdbclient.view;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.adapter.MovieAdapter;
import com.androidtutz.anushka.tmdbclient.databinding.ActivityMainBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.model.MovieDBResponse;
import com.androidtutz.anushka.tmdbclient.service.MovieDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        getSupportActionBar().setTitle("TMDB Popular Movies Today");
        
        getPopularMovies();
        
        binding.swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getPopularMovies();
            }
        });
    }
    
    public void getPopularMovies()
    {
        MovieDataService movieDataService = RetrofitInstance.getService();
        
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(this.getString(R.string.api_key));
        
        call.enqueue(new Callback<MovieDBResponse>()
        {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response)
            {
                MovieDBResponse movieDBResponse = response.body();
                
                if ( movieDBResponse != null && movieDBResponse.getMovies() != null )
                {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    showOnRecyclerView();
                }
            }
            
            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t)
            {
            
            }
        });
    }
    
    private void showOnRecyclerView()
    {
        movieAdapter = new MovieAdapter(this, movies);
        
        if ( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
        {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else
        {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(this, 4));
        }
        
        binding.rvMovies.setItemAnimator(new DefaultItemAnimator());
        binding.rvMovies.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
