package com.androidtutz.anushka.tmdbclient.di;

import com.androidtutz.anushka.tmdbclient.model.PopularMoviesRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class // Important -- this is how we pass the application context to Dagger.
})
public interface AppComponent
{
    PopularMoviesRepository getPopularMoviesRepository(); // If we wire the app up properly, this won't be necessary.
    
    // Notice there aren't any inject methods because I'm not using Dagger in any activities yet.
}
