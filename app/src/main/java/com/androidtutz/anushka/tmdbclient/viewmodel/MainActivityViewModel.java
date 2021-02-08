package com.androidtutz.anushka.tmdbclient.viewmodel;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.model.PopularMoviesRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel
{
    private PopularMoviesRepository popularMoviesRepository;
    
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        popularMoviesRepository = new PopularMoviesRepository(application);
    }
    
    public LiveData<ArrayList<Movie>> getPopularMovies()
    {
        return popularMoviesRepository.getPopularMovies();
    }
}
