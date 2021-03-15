package com.androidtutz.anushka.tmdbclient.viewmodel;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.TMDBApp;
import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.model.PopularMoviesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

// AndroidViewModel has context
public class MainActivityViewModel extends AndroidViewModel
{
    private PopularMoviesRepository popularMoviesRepository;
    
    // If we create the view model with a factory, we can pass the repository in the constructor.
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
    
        // For now, just get the repository directly from the component (which is in the TMDBApp class).
        popularMoviesRepository = ((TMDBApp) application).getDaggerAppComponent().getPopularMoviesRepository();
    }
    
    public LiveData<List<Movie>> getPopularMovies()
    {
        return popularMoviesRepository.getMutableLiveData();
    }
}
