package io.chuumong.booksearch.date.remote;

import com.google.gson.GsonBuilder;

import java.net.URL;

import io.chuumong.booksearch.BuildConfig;
import io.chuumong.booksearch.date.remote.api.ApiService;
import io.chuumong.booksearch.date.remote.model.Search;
import io.chuumong.booksearch.date.remote.util.ApiKeyInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public class NetworkRequestModule {

    private static final String BASE_URL = "https://openapi.naver.com/v1/search/";

    private static ApiService apiService;

    private static ApiService getService() {
        if (apiService == null) {
            apiService = getApiService();
        }

        return apiService;
    }

    private static ApiService getApiService() {
        final OkHttpClient okHttpClient = getOkHttpClient();
        final Retrofit retrofit = getRetrofit(okHttpClient);

        return retrofit.create(ApiService.class);
    }

    private static Retrofit getRetrofit(OkHttpClient okHttpClient) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(
                GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().create()));

        return builder.build();
    }

    private static OkHttpClient getOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(new ApiKeyInterceptor());

        return builder.build();
    }

    public static void requestSearchBook(String qurey, int display, int start, Callback<Search> callback) {
        getService().getSearchBook(qurey, display, start).enqueue(callback);
    }
}
