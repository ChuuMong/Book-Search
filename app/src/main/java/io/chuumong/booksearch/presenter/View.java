package io.chuumong.booksearch.presenter;


public interface View {

    void showErrorMessage(Throwable throwable);

    void showProgress();

    void hideProgress();
}
