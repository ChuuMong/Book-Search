package io.chuumong.booksearch.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.model.Book;
import io.chuumong.booksearch.data.model.Search;
import io.chuumong.booksearch.presenter.search.SearchPresenter;
import io.chuumong.booksearch.ui.adapter.SearchAdapter;
import io.chuumong.booksearch.ui.view.InfiniteScrollListener;

public class SearchFragment extends DaggerFragment implements io.chuumong.booksearch.presenter.search.SearchView,
        SearchAdapter.OnClickBookItemListener {

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

    @BindView(R.id.progress)
    ProgressBar progress;

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
        searchAdapter.setListener(this);

        rvSearch.addOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void onLoadMore() {
                presenter.moreGetSearch();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit#query : " + query);
                presenter.sendSearchQuery(query);
                showListSearch(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        showListSearch(false);
    }

    @Override
    public void sendSearchResult(Search search) {
        searchAdapter.clear();
        searchAdapter.add(search);
        checkListSearchItem();
    }

    @Override
    public void moreSearchResult(Search search) {
        searchAdapter.add(search);
        checkListSearchItem();
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

    @Override
    public void onClickBookItem(Book book) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(book.getLink())));
    }
}
