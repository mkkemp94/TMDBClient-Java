package com.androidtutz.anushka.tmdbclient.viewmodel;

import android.app.Application;

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
    
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        popularMoviesRepository = new PopularMoviesRepository(application);
    }
    
    public LiveData<List<Movie>> getPopularMovies()
    {
        return popularMoviesRepository.getMutableLiveData();
    }
}
