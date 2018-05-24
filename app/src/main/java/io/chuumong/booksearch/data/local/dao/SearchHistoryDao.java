package io.chuumong.booksearch.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.data.remote.model.Search;
import io.reactivex.Single;

/**
 * Created by jonghunlee on 2018-05-24.
 */

@Dao
public interface SearchHistoryDao {

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    Single<List<SearchHistory>> getAll();

    @Insert
    void insert(SearchHistory searchHistory);

    @Query("DELETE FROM search_history WHERE id = :id")
    void delete(long id);

    @Delete
    void delete(SearchHistory history);
}
