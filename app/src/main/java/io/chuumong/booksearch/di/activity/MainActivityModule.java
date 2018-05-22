package io.chuumong.booksearch.di.activity;


import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.chuumong.booksearch.di.fragment.FragmentScoped;
import io.chuumong.booksearch.ui.activity.MainActivity;
import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchListFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

@Module
public abstract class MainActivityModule {

    @Provides
    @ActivityScoped
    static FragmentManager provideFragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScoped
    static LinearLayoutManager provideLinearLayoutManager(MainActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchListFragment searchListFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SettingFragment settingFragment();
}
