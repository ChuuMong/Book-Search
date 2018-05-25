package io.chuumong.booksearch.date.remote.model;

import java.util.List;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public class Search {
    private int total;
    private List<Book> items;

    public int getTotal() {
        return total;
    }

    public List<Book> getBooks() {
        return items;
    }
}
