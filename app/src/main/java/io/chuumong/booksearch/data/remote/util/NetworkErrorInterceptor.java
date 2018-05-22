package io.chuumong.booksearch.data.remote.util;


import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkErrorInterceptor implements Interceptor {

    private static final String TAG = NetworkErrorInterceptor.class.getSimpleName();

    /**
     * 재시도 횟수
     */
    private static final int RETRY_COUNT = 3;

    /**
     * 재시도 딜레이 타임
     */
    private static final int RETRY_DELAY_TIME = 5 * 1000;

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        int tryCount = 0;
        while ((response == null || !response.isSuccessful()) && tryCount < RETRY_COUNT) {
            Log.e(TAG, "intercept#request fail tryCount : " + tryCount);

            try {
                Thread.sleep(RETRY_DELAY_TIME);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            tryCount++;

            // Request 재 시도
            response = chain.proceed(request);
        }

        if (response == null) {
            throw new IOException("REQUEST FAIL");
        }

        return response;
    }
}