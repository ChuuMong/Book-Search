package io.chuumong.booksearch.data.remote;

import com.google.gson.GsonBuilder;

import io.chuumong.booksearch.BuildConfig;
import io.chuumong.booksearch.data.remote.api.ApiService;
import io.chuumong.booksearch.data.remote.model.Search;
import io.chuumong.booksearch.data.remote.util.ApiKeyInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public class NetworkRequestModule {

    private static final String BASE_URL = "https://openapi.naver.com/v1/search/";

    private static ApiService apiService;

    private static OkHttpClient getOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(new ApiKeyInterceptor());

        return builder.build();
    }

    private static Retrofit getRetrofit() {
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getOkHttpClient());
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(
                GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().create()));

        return builder.build();
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }

        return apiService;
    }

    public static void requestSearchBook(String query, int display, int start, Callback<Search> callback) {
        getApiService().getSearchBook(query, display, start).enqueue(callback);
    }
}
