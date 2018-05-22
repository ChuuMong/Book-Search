package io.chuumong.booksearch.presenter.search;

import java.util.List;

import io.chuumong.booksearch.data.model.Search;
import io.chuumong.booksearch.presenter.View;

public interface SearchView extends View {

    void sendSearchResult(Search search);

    void moreSearchResult(Search search);
}
