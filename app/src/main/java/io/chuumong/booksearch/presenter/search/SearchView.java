package io.chuumong.booksearch.presenter.search;

import io.chuumong.booksearch.data.remote.model.Search;
import io.chuumong.booksearch.presenter.View;

public interface SearchView extends View {

    void sendSearchResult(Search search);

    void moreSearchResult(Search search);
}
