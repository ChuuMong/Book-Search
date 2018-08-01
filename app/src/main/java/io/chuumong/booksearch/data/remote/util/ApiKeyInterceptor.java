package io.chuumong.booksearch.data.remote.util;

import android.os.Build;

import java.io.IOException;

import io.chuumong.booksearch.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.API_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.API_CLIENT_SECRET)
                .build();

        return chain.proceed(request);
    }
}
