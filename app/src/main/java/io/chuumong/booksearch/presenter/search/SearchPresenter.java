package io.chuumong.booksearch.presenter.search;

import android.util.Log;

import java.util.Date;

import javax.inject.Inject;

import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.data.repository.SearchHistoryRepository;
import io.chuumong.booksearch.data.repository.SearchRepository;
import io.chuumong.booksearch.presenter.Presenter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends Presenter<SearchView> {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    private static final int MAX_SEARCH_COUNT = 20;

    private final SearchRepository searchRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    private int currentStart = 1;
    private int totalCount = 0;

    private String query;
    private boolean isSearch = false;

    @Inject
    public SearchPresenter(SearchRepository searchRepository, SearchHistoryRepository searchHistoryRepository) {
        this.searchRepository = searchRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public void sendSearchQuery(String query) {
        checkViewIsNull();
        if (isSearch) {
            return;
        }

        view.showProgress();

        initSearchInfo(query);
        setIsSearch(true);

        disposable.add(searchRepository.getSearchBooks(query, MAX_SEARCH_COUNT, currentStart)
                .doFinally(() -> {
                    setIsSearch(false);
                    view.hideProgress();
                })
                .subscribe(search -> {
                            totalCount = search.getTotal();
                            view.sendSearchResult(search);
                            saveSearch(query);
                        },
                        throwable -> view.showErrorMessage(throwable)));
    }

    private void saveSearch(String query) {
        disposable.add(searchHistoryRepository.insertSearchHistory(
                new SearchHistory(0, query, new Date().getTime()))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.d(TAG, "saveSearch#finish save search"),
                        throwable -> view.showErrorMessage(throwable)));
    }

    public void moreGetSearch() {
        checkViewIsNull();
        if (isSearch || checkMaxTotalCount()) {
            return;
        }

        view.showProgress();

        setIsSearch(true);
        addSearchCount();

        disposable.add(searchRepository.getSearchBooks(query, MAX_SEARCH_COUNT, currentStart)
                .doFinally(() -> {
                    setIsSearch(false);
                    view.hideProgress();
                })
                .subscribe(search -> view.moreSearchResult(search),
                        throwable -> view.showErrorMessage(throwable)));
    }

    private void initSearchInfo(String query) {
        currentStart = 1;
        totalCount = 0;
        this.query = query;
    }

    private void setIsSearch(boolean isSearch) {
        this.isSearch = isSearch;
    }

    private void addSearchCount() {
        currentStart += MAX_SEARCH_COUNT;
    }

    private boolean checkMaxTotalCount() {
        return totalCount <= currentStart;
    }
}
