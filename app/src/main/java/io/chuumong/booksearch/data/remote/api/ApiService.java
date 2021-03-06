package io.chuumong.booksearch.data.remote.api;

import io.chuumong.booksearch.data.remote.model.Search;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("book.json")
    Single<Search> getSearchBooks(@Query("query") String query, @Query("display") int display, @Query("start") int start);
}
