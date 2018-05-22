package io.chuumong.booksearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import io.chuumong.booksearch.presenter.search.SearchView;
import io.chuumong.booksearch.ui.adapter.SearchAdapter;

public class SearchFragment extends DaggerFragment implements SearchView {

    @Inject
    public SearchFragment() {
    }

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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);
        presenter.attachView(this);

        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(searchAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showListSearch(false);
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
