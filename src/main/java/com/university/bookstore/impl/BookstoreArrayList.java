package com.university.bookstore.impl;
import com.university.bookstore.api.BookstoreAPI;
import com.university.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BookstoreArrayList implements BookstoreAPI {
    private final List<Book> books;

    public BookstoreArrayList() {
        this.books = new ArrayList<>();
    }

    public boolean add(Book book) {
        if (book == null) {
            return false;
        }

        for (Book currBook : books) {
            if (currBook.getIsbn().equals(book.getIsbn())) {
                return false;
            }
        }

        return books.add(book);
    }


    public Book findByIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }

        for (Book currBook: books) {
            if (currBook.getIsbn().equals(isbn)) {
                return currBook;
            }
        }

        return null;
    }

    public boolean removeByIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }

        for (Book currBook: books) {
            if (currBook.getIsbn().equals(isbn)) {
                books.remove(currBook);
                return true;
            }
        }

        return false;
    }

    public List<Book> findByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<>();
        title = title.trim().toLowerCase();

        for (Book currBook: books) {
            String bookTitle = currBook.getTitle().trim().toLowerCase();
            if (bookTitle.contains(title)) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }

    public List<Book> findByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return null;
        }

        author = author.trim().toLowerCase();

        ArrayList<Book> bookList = new ArrayList<>();

        for (Book currBook: books) {
            if (currBook.getAuthor().toLowerCase().contains(author)) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }

    public List<Book> findByPriceRange(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }

        if (min < 0 || max <= 0) {
            throw new IllegalArgumentException("Price is invalid");
        }

        ArrayList<Book> bookList = new ArrayList<>();

        for (Book currBook: books) {
            if (currBook.getPrice() >= min && currBook.getPrice() <= max) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }

    public List<Book> findByYear(int year) {
        if (year <= 0 || year > 2025) {
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<>();
        for (Book currBook: books) {
            if (currBook.getYear() == year) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }


    public int size() {
        return this.books.size();
    }

    public double inventoryValue() {
        double inventoryValue = 0;

        for (Book currBook: books) {
            inventoryValue += currBook.getPrice();
        }

        return inventoryValue;
    }

    public Book getMostExpensive() {
        Book mostExpensive = null;
        double maxPrice = 0;

        for (Book currBook: books) {
            if (currBook.getPrice() >= maxPrice) {
                maxPrice = currBook.getPrice();
                mostExpensive = currBook;
            }
        }

        return mostExpensive;
    }

    public Book getMostRecent() {
        Book mostRecent = null;
        int mostRecentYear = 0;

        for (Book currBook: books) {
            if (currBook.getYear() > mostRecentYear) {
                mostRecent = currBook;
                mostRecentYear = currBook.getYear();
            }
        }

        return mostRecent;
    }

    public Book[] snapshotArray() {
        Book[] bookArray = new Book[this.size()];

        bookArray = this.books.toArray(bookArray);
        return bookArray;
    }

    public List<Book> getAllBooks() {
        ArrayList<Book> newBookList = new ArrayList<>();
        for (Book currBook: books) {
            newBookList.add(currBook);
        }

        return newBookList;
    }
}