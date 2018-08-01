package io.chuumong.booksearch.data.remote.util;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class MainThreadCallAdapter extends CallAdapter.Factory {

    public static MainThreadCallAdapter create(Scheduler scheduler) {
        return new MainThreadCallAdapter(scheduler);
    }

    private final Scheduler scheduler;

    private MainThreadCallAdapter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @SuppressWarnings("NullableProblems")
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) == Observable.class) {
            return observableCallAdapter(returnType, annotations, retrofit);
        }

        if (getRawType(returnType) == Single.class) {
            return singleCallAdapter(returnType, annotations, retrofit);
        }

        return null;
    }

    private CallAdapter<?, ?> observableCallAdapter(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        @SuppressWarnings("unchecked") final CallAdapter<Object, Observable<?>> delegate =
                (CallAdapter<Object, Observable<?>>) retrofit.nextCallAdapter(this, returnType,
                        annotations);

        return new CallAdapter<Object, Object>() {
            @Override
            public Object adapt(@NonNull Call<Object> call) {
                return delegate.adapt(call).observeOn(scheduler);
            }

            @Override
            public Type responseType() {
                return delegate.responseType();
            }
        };
    }

    private CallAdapter<?, ?> singleCallAdapter(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        @SuppressWarnings("unchecked") final CallAdapter<Object, Single<?>> delegate =
                (CallAdapter<Object, Single<?>>) retrofit.nextCallAdapter(this, returnType,
                        annotations);

        return new CallAdapter<Object, Object>() {
            @Override
            public Object adapt(@NonNull Call<Object> call) {
                return delegate.adapt(call).observeOn(scheduler);
            }

            @Override
            public Type responseType() {
                return delegate.responseType();
            }
        };
    }
}