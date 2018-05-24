package io.chuumong.booksearch.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.chuumong.booksearch.data.local.AppDatabase;
import io.chuumong.booksearch.data.local.dao.SearchHistoryDao;

/**
 * Created by jonghunlee on 2018-05-24.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideDataBase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME).build();
    }

    @Provides
    @Singleton
    public SearchHistoryDao provideSearchHistoryDao(AppDatabase appDatabase) {
        return appDatabase.searchHistoryDao();
    }
}
