package io.chuumong.booksearch.date.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.chuumong.booksearch.date.local.dao.SearchHistoryDao;
import io.chuumong.booksearch.date.local.model.SearchHistory;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public class DatabaseModule {

    private static AppDatabase appDatabase;
    private static SearchHistoryDao searchHistoryDao;

    private static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return appDatabase;
    }

    private static SearchHistoryDao getSearchHistoryDao(Context context) {
        if (searchHistoryDao == null) {
            searchHistoryDao = getAppDatabase(context).searchHistoryDao();
        }

        return searchHistoryDao;
    }

    public static void save(Context context, SearchHistory searchHistory) {
        getSearchHistoryDao(context).insert(searchHistory);
    }

    public static List<SearchHistory> getAll(Context context) {
        return getSearchHistoryDao(context).getAllHistory();
    }

    public static void delete(Context context, long id) {
        getSearchHistoryDao(context).delete(id);
    }
}
