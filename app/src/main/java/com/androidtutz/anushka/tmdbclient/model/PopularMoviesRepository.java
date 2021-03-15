package com.androidtutz.anushka.tmdbclient.model;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.service.MovieDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesRepository
{
    private Application application;
    
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    
    @Inject // This annotation tells Dagger that it needs to generate code to add this object to its graph.
    public PopularMoviesRepository(Application application)
    {
        this.application = application;
    }
    
    // Getter
    public MutableLiveData<List<Movie>> getMutableLiveData()
    {
        MovieDataService movieDataService = RetrofitInstance.getService();
    
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
    
        call.enqueue(new Callback<MovieDBResponse>()
        {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response)
            {
                MovieDBResponse movieDBResponse = response.body();
    
                if ( movieDBResponse != null && movieDBResponse.getMovies() != null )
                {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.postValue(movies);
                }
            }
        
            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t)
            {
            
            }
        });
        
        return mutableLiveData;
    }
}
