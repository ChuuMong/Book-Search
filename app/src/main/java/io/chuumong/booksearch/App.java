package io.chuumong.booksearch;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.chuumong.booksearch.di.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
