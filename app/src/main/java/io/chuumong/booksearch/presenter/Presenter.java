package io.chuumong.booksearch.presenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class Presenter<V extends View> {

    protected final CompositeDisposable subscription;

    protected V view;

    public Presenter() {
        this.subscription = new CompositeDisposable();
    }

    public void attachView(V view) {
        this.view = view;
    }

    protected void checkViewIsNull() {
        if (view == null) {
            throw new IllegalArgumentException("View is null");
        }
    }

    public void detachView() {
        subscription.dispose();

        view = null;
    }

}
