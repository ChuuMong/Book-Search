package io.chuumong.booksearch.di.activity;


import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.chuumong.booksearch.di.fragment.FragmentScoped;
import io.chuumong.booksearch.ui.activity.MainActivity;
import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchHistoryFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

@Module
public abstract class MainActivityModule {

    @Provides
    @ActivityScoped
    static FragmentManager provideFragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchHistoryFragment searchHistoryFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SettingFragment settingFragment();
}
