package com.androidtutz.anushka.tmdbclient.viewmodel;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.model.Movie;
import com.androidtutz.anushka.tmdbclient.model.MovieDataSource;
import com.androidtutz.anushka.tmdbclient.model.MovieDataSourceFactory;
import com.androidtutz.anushka.tmdbclient.service.MovieDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

// AndroidViewModel has context
public class MainActivityViewModel extends AndroidViewModel
{
//    private PopularMoviesRepository popularMoviesRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;
    
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
//        popularMoviesRepository = new PopularMoviesRepository(application);
    
        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();
    
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        
        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }
    
//    public LiveData<List<Movie>> getPopularMovies()
//    {
//        return popularMoviesRepository.getMutableLiveData();
//    }
    
    public LiveData<PagedList<Movie>> getMoviesPagedList()
    {
        return moviesPagedList;
    }
}
