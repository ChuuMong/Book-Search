package io.chuumong.booksearch.ui.fragment;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.remote.NetworkRequestModule;
import io.chuumong.booksearch.data.remote.model.Book;
import io.chuumong.booksearch.data.remote.model.Search;
import io.chuumong.booksearch.ui.adapter.SearchAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by jonghunlee on 2018-05-25.
 */
public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    private static final int MAX_SEARCH_COUNT = 20;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.list_search)
    RecyclerView rvSearch;

    @BindView(R.id.layout_empty_list_message)
    FrameLayout flEmptyListMessage;

    @BindView(R.id.progress)
    ProgressBar progress;

    private SearchAdapter searchAdapter;

    private int currentStart = 1;
    private int totalCount = 0;
    private String currentSearchQuery;
    private boolean isSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestSearchBook(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void requestSearchBook(final String query) {
        NetworkRequestModule.requestSearchBook(query, MAX_SEARCH_COUNT, currentStart,
                new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {

                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {

                    }
                });
    }

    private void moreRequestSearchBook() {

    }

    private void initSearchInfo(String query) {
        currentStart = 1;
        totalCount = 0;
        currentSearchQuery = query;
    }

    private void setIsSearch(boolean isSearch) {
        this.isSearch = isSearch;
    }

    private void addSearchCount() {
        currentStart += MAX_SEARCH_COUNT;
    }

    private boolean checkMaxTotalCount() {
        return totalCount <= currentStart;
    }

    private void checkListSearchItem() {
//        if (searchAdapter.getItemCount() == 0) {
//            showListSearch(false);
//        } else {
//            showListSearch(true);
//        }
    }

    private void showListSearch(boolean isShow) {
        rvSearch.setVisibility(isShow ? View.VISIBLE : View.GONE);
        flEmptyListMessage.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    public void sendSearch(String search) {
        searchView.onActionViewExpanded();
        searchView.setQuery(search, true);
    }

}