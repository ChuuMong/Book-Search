package io.chuumong.booksearch.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.chuumong.booksearch.App;
import io.chuumong.booksearch.di.activity.ActivityBindingModule;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DatabaseModule.class, ActivityBindingModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
