package io.chuumong.booksearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.presenter.searchhistory.SearchHistoryPresenter;
import io.chuumong.booksearch.presenter.searchhistory.SearchHistoryView;
import io.chuumong.booksearch.ui.activity.MainActivity;
import io.chuumong.booksearch.ui.adapter.SearchHistoryAdapter;

public class SearchHistoryFragment extends DaggerFragment implements SearchHistoryView, SearchHistoryAdapter.OnClickSearchHistoryListener {

    private static final String TAG = SearchHistoryFragment.class.getSimpleName();

    @BindView(R.id.list_search_history)
    RecyclerView rvSearchHistory;

    @BindView(R.id.progress)
    ProgressBar progress;

    @Inject
    SearchHistoryPresenter presenter;

    @Inject
    SearchHistoryAdapter adapter;

    @Inject
    public SearchHistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        presenter.attachView(this);

        rvSearchHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSearchHistory.setAdapter(adapter);

        adapter.setListener(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            presenter.getAllHistory();
        }

        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void resultSearchHistory(List<SearchHistory> searchHistories) {
        adapter.addAll(searchHistories);
    }

    @Override
    public void resultDelete(int position) {
        Log.d(TAG, "resultDelete#delete : " + position);
        adapter.delete(position);
    }

    @Override
    public void onClickItem(SearchHistory searchHistory) {
        ((MainActivity) getActivity()).sendSearch(searchHistory.getSearch());
    }

    @Override
    public void onClickDelete(SearchHistory searchHistory, int position) {
        presenter.deleteHistory(searchHistory, position);
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Log.e(TAG, "showErrorMessage", throwable);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
