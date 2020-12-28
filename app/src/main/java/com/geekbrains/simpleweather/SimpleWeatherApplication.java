package com.geekbrains.simpleweather;

import android.app.Application;

import timber.log.Timber;

public class SimpleWeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
