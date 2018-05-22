package io.chuumong.booksearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.presenter.search.SearchPresenter;
import io.chuumong.booksearch.ui.adapter.SearchAdapter;

public class SearchFragment extends DaggerFragment implements io.chuumong.booksearch.presenter.search.SearchView {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @Inject
    public SearchFragment() {
    }

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.list_search)
    RecyclerView rvSearch;

    @BindView(R.id.layout_empty_list_message)
    FrameLayout flEmptyListMessage;

    @Inject
    SearchPresenter presenter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        presenter.attachView(this);

        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit#query : " + query);
                presenter.sendQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        showListSearch(false);
    }

    private void checkListSearchItem() {
        if (searchAdapter.getItemCount() == 0) {
            showListSearch(false);
        } else {
            showListSearch(true);
        }
    }

    private void showListSearch(boolean isShow) {
        rvSearch.setVisibility(isShow ? View.VISIBLE : View.GONE);
        flEmptyListMessage.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showErrorMessage(Throwable throwable) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
