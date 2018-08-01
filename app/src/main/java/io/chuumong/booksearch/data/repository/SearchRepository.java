package io.chuumong.booksearch.data.repository;

import javax.inject.Inject;

import io.chuumong.booksearch.data.remote.model.Search;
import io.chuumong.booksearch.data.remote.api.ApiService;
import io.reactivex.Single;

public class SearchRepository {

    private final ApiService service;

    @Inject
    SearchRepository(ApiService service) {
        this.service = service;
    }

    public Single<Search> getSearchBooks(String query, int display, int start) {
        return service.getSearchBooks(query, display, start);
    }

}
