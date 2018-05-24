package io.chuumong.booksearch.presenter.searchhistory;

import javax.inject.Inject;

import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.data.repository.SearchHistoryRepository;
import io.chuumong.booksearch.presenter.Presenter;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jonghunlee on 2018-05-24.
 */
public class SearchHistoryPresenter extends Presenter<SearchHistoryView> {

    private SearchHistoryRepository repository;

    @Inject
    public SearchHistoryPresenter(SearchHistoryRepository repository) {
        this.repository = repository;
    }

    public void getAllHistory() {
        checkViewIsNull();

        view.showProgress();

        disposable.add(repository.getSearchHistoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.hideProgress())
                .subscribe(searchHistories -> view.resultSearchHistory(searchHistories),
                        throwable -> view.showErrorMessage(throwable)));
    }

    public void deleteHistory(SearchHistory searchHistory, int position) {
        checkViewIsNull();

        view.showProgress();

        disposable.add(repository.deleteSearchHistory(searchHistory.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.hideProgress())
                .subscribe(() -> view.resultDelete(position),
                        throwable -> view.showErrorMessage(throwable)));
    }
}
