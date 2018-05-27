package io.chuumong.booksearch.data.remote.api;

import io.chuumong.booksearch.data.remote.model.Search;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public interface ApiService {


    // BASE_URL/book.json?query=${query}&display=${display}&start=${start}
    @GET("book.json")
    Call<Search> getSearchBook(@Query("query") String query,
                               @Query("display") int display,
                               @Query("start") int start);
}
