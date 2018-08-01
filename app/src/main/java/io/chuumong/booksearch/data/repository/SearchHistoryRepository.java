package io.chuumong.booksearch.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.chuumong.booksearch.data.local.AppDatabase;
import io.chuumong.booksearch.data.local.dao.SearchHistoryDao;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.data.remote.model.Search;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by jonghunlee on 2018-05-24.
 */
public class SearchHistoryRepository {

    private final SearchHistoryDao dao;

    @Inject
    public SearchHistoryRepository(SearchHistoryDao dao) {
        this.dao = dao;
    }

    public Single<List<SearchHistory>> getSearchHistoryList() {
        return dao.getAll();
    }

    public Completable insertSearchHistory(SearchHistory searchHistory) {
        return Completable.fromAction(() -> dao.insert(searchHistory));
    }

    public Completable deleteSearchHistory(SearchHistory searchHistory) {
        return Completable.fromAction(() -> dao.delete(searchHistory));
    }

    public Completable deleteSearchHistory(long id) {
        return Completable.fromAction(() -> dao.delete(id));
    }
}
