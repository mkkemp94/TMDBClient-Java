package com.androidtutz.anushka.tmdbclient.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    private final Application application;
    
    // Give the application context to this class via a constructor.
    public AppModule(Application application)
    {
        this.application = application;
    }
    
    @Provides
    public Application provideApplication()
    {
        // Provide the application context to the Dagger graph using a @Provides method.
        return application;
    }
}
