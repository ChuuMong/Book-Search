package io.chuumong.booksearch.data.local.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jonghunlee on 2018-05-24.
 */

@Entity(tableName = "search_history", indices = {@Index("id")})
public class SearchHistory {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String search;

    private long date;

    public SearchHistory(long id, String search, long date) {
        this.id = id;
        this.search = search;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getSearch() {
        return search;
    }

    public long getDate() {
        return date;
    }
}
