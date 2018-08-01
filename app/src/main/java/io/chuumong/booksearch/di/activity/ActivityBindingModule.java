package io.chuumong.booksearch.di.activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.chuumong.booksearch.ui.activity.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
