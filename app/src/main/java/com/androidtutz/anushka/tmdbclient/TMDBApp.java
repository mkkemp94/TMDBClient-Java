package com.androidtutz.anushka.tmdbclient;

import android.app.Application;

import com.androidtutz.anushka.tmdbclient.di.AppComponent;
import com.androidtutz.anushka.tmdbclient.di.AppModule;
import com.androidtutz.anushka.tmdbclient.di.DaggerAppComponent;

public class TMDBApp extends Application
{
    private AppComponent daggerAppComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        // This is where we are creating the Dagger component.
        daggerAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)) // Remember to pass the application context to the AppModule.
                .build();
    }
    
    // Provide a getter so that we can access the component outside this class.
    // If the app is wired up properly, this isn't necessary.
    public AppComponent getDaggerAppComponent()
    {
        return daggerAppComponent;
    }
}
