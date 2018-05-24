package io.chuumong.booksearch.presenter.searchhistory;

import java.util.List;

import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.presenter.View;

/**
 * Created by jonghunlee on 2018-05-24.
 */
public interface SearchHistoryView extends View {

    void resultSearchHistory(List<SearchHistory> searchHistories);

    void resultDelete(int position);

}
