package io.chuumong.booksearch.data.local;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import io.chuumong.booksearch.data.local.dao.SearchHistoryDao;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.data.remote.model.Book;

/**
 * Created by jonghunlee on 2018-05-24.
 */
@Database(version = 1, entities = {SearchHistory.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_db";

    abstract public SearchHistoryDao searchHistoryDao();
}
