package io.chuumong.booksearch.di;


import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.chuumong.booksearch.BuildConfig;
import io.chuumong.booksearch.data.remote.api.ApiService;
import io.chuumong.booksearch.data.remote.util.ApiKeyIntercepter;
import io.chuumong.booksearch.data.remote.util.MainThreadCallAdapter;
import io.chuumong.booksearch.data.remote.util.NetworkErrorInterceptor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(new ApiKeyIntercepter());
        builder.addInterceptor(new NetworkErrorInterceptor());

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl, OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(client);
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(
                GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting()
                        .create()));
        builder.addCallAdapterFactory(MainThreadCallAdapter.create(AndroidSchedulers.mainThread()));
        builder.addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        return builder.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    String provideBaseUrl() {
        return "https://openapi.naver.com/v1/search/";
    }
}
