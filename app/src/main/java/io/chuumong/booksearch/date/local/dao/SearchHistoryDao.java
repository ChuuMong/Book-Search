package io.chuumong.booksearch.date.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.chuumong.booksearch.date.local.model.SearchHistory;

/**
 * Created by jonghunlee on 2018-05-25.
 */

@Dao
public interface SearchHistoryDao {

    @Insert
    void insert(SearchHistory searchHistory);

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    List<SearchHistory> getAllHistory();

    @Query("DELETE FROM search_history WHERE id = :id")
    void delete(long id);

    @Delete
    void delete(SearchHistory searchHistory);

}
