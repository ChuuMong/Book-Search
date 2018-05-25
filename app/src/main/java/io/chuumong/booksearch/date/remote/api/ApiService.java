package io.chuumong.booksearch.date.remote.api;

import io.chuumong.booksearch.date.remote.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public interface ApiService {

    @GET("book.json")
    Call<Search> getSearchBook(@Query("query") String query, @Query("display") int display, @Query("start") int start);
}
