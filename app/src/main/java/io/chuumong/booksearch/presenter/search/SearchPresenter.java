package io.chuumong.booksearch.presenter.search;

import javax.inject.Inject;

import io.chuumong.booksearch.data.repository.SearchRepository;
import io.chuumong.booksearch.presenter.Presenter;

public class SearchPresenter extends Presenter<SearchView> {

    private static final int MAX_SEARCH_COUNT = 20;

    private final SearchRepository repository;

    private int currentStart = 1;

    @Inject
    public SearchPresenter(SearchRepository repository) {
        this.repository = repository;
    }


    public void sendQuery(String query) {
        checkViewIsNull();

        disposable.add(repository.getSearchBooks(query, MAX_SEARCH_COUNT, currentStart)
                .subscribe(books -> {

                }, throwable -> {

                }));
    }
}
