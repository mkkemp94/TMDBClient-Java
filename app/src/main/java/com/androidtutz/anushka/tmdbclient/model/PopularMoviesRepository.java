package com.androidtutz.anushka.tmdbclient.model;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.service.MovieDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesRepository
{
    private Application application;
    
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    
    public PopularMoviesRepository(Application application)
    {
        this.application = application;
    }
    
    public MutableLiveData<ArrayList<Movie>> getPopularMovies()
    {
        MovieDataService movieDataService = RetrofitInstance.getService();
    
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(application.getString(R.string.api_key));
    
        call.enqueue(new Callback<MovieDBResponse>()
        {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response)
            {
                MovieDBResponse movieDBResponse = response.body();
    
                if ( movieDBResponse != null && movieDBResponse.getMovies() != null )
                {
                    ArrayList<Movie> movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    PopularMoviesRepository.this.movies.postValue(movies);
                }
            }
        
            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t)
            {
            
            }
        });
        
        return movies;
    }
}
