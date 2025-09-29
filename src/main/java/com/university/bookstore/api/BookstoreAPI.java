package com.university.bookstore.api;
import java.util.List;

import com.university.bookstore.model.Book;

public interface BookstoreAPI {
    boolean add(Book book);
    boolean removeByIsbn(String isbn);
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String titleQuery);
    List<Book> findByAuthor(String authorQuery);
    List<Book> findByPriceRange(double minPrice, double maxPrice);
    List<Book> findByYear(int year);
    // Analytics operations
    int size();
    double inventoryValue();
    Book getMostExpensive();
    Book getMostRecent();

    // Export operations
    Book[] snapshotArray();
    List<Book> getAllBooks();

}
