package com.androidtutz.anushka.tmdbclient.model;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.service.MovieDataService;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory
{
    private MovieDataService movieDataService;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;
    
    public MovieDataSourceFactory(MovieDataService movieDataService, Application application)
    {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }
    
    @NonNull
    @Override
    public DataSource create()
    {
        MovieDataSource movieDataSource = new MovieDataSource(movieDataService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
    
    public MutableLiveData<MovieDataSource> getMutableLiveData()
    {
        return mutableLiveData;
    }
}
