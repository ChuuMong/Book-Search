package io.chuumong.booksearch.presenter.search;

import javax.inject.Inject;

import io.chuumong.booksearch.data.repository.SearchRepository;
import io.chuumong.booksearch.presenter.Presenter;

public class SearchPresenter extends Presenter<SearchView> {

    private static final int MAX_SEARCH_COUNT = 20;

    private final SearchRepository repository;

    private int currentStart = 1;
    private int totalCount = 0;

    private String query;
    private boolean isSearch = false;

    @Inject
    public SearchPresenter(SearchRepository repository) {
        this.repository = repository;
    }

    public void sendSearchQuery(String query) {
        checkViewIsNull();
        if (isSearch) {
            return;
        }

        view.showProgress();

        initSearchInfo(query);
        setIsSearch(true);

        disposable.add(repository.getSearchBooks(query, MAX_SEARCH_COUNT, currentStart)
                .doFinally(() -> {
                    setIsSearch(false);
                    view.hideProgress();
                })
                .subscribe(search -> {
                            totalCount = search.getTotal();
                            view.sendSearchResult(search);
                        },
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

        disposable.add(repository.getSearchBooks(query, MAX_SEARCH_COUNT, currentStart)
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
