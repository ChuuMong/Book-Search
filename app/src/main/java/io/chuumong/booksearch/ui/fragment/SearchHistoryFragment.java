package io.chuumong.booksearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.ui.adapter.SearchHistoryAdapter;


/**
 * Created by jonghunlee on 2018-05-25.
 */
public class SearchHistoryFragment extends Fragment {

    public static SearchHistoryFragment newInstance() {
        return new SearchHistoryFragment();
    }

    @BindView(R.id.list_search_history)
    RecyclerView rvSearchHistory;

    @BindView(R.id.progress)
    ProgressBar progress;

    private SearchHistoryAdapter searchHistoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
//            progress.setVisibility(View.VISIBLE);
//            List<SearchHistory> searchHistories = DatabaseModule.getAll(getActivity());
//            searchHistoryAdapter.addAll(searchHistories);
//            progress.setVisibility(View.GONE);
        }

        super.setUserVisibleHint(isVisibleToUser);
    }

}
