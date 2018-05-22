package io.chuumong.booksearch.data.model;

import java.util.List;

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
