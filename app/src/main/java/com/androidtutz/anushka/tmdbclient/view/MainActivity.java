package com.androidtutz.anushka.tmdbclient.view;

import android.content.res.Configuration;
import android.os.Bundle;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.adapter.MovieAdapter;
import com.androidtutz.anushka.tmdbclient.databinding.ActivityMainBinding;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    
    private MainActivityViewModel mainActivityViewModel;
    
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        mainActivityViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainActivityViewModel.class);
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
    
    private void getPopularMovies()
    {
        mainActivityViewModel.getPopularMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> list)
            {
                movies = list;
                showOnRecyclerView();
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
