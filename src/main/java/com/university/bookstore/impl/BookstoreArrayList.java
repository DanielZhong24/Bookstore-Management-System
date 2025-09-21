package com.university.bookstore.impl;
import com.university.bookstore.api.BookstoreAPI;
import com.university.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Bookstore API implemented using Java ArrayLists
 *
 * <p>This class uses an ArrayList to hold books in an "inventory" and allows
 * it only so unique ISBN's can be added to the inventory, duplicate ISBNs are denied.
 * A duplicate/defensive copy is returned so the original data is not affected</p>
 *
 * @author Andrew Cao
 * @version 1.0
 * @since 2025-09-21
 *
 * @see BookstoreAPI
 * @see Book
 *
 */

public class BookstoreArrayList implements BookstoreAPI {
    private final List<Book> books;

    /**
     * Create an empty bookstore
     */
    public BookstoreArrayList() {
        this.books = new ArrayList<>();
    }

    /**
     * Adds a book to the bookstore, but checks if it is null or already
     * exists by ISBN before adding
     * O(n) time complexity - Has to check if book already exists
     * @param book, a book object
     * @return false if null or already exists, true otherwise
     */
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


    /**
     * Searches for a book by the given ISBN code
     * O(n) time complexity - Has to check each book for its ISBN
     * @param isbn, a 10 or 13 length code
     * @return Book, the book by the ISBN if found, otherwise null
     */
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

    /**
     * Removes a book from the bookstore when provided a ISBN
     * O(n) time complexity - Has to check each book for its ISBN before removing
     * @param isbn, a 10 or 13 length code
     * @return boolean, true if a book with the ISBN is found, false otherwise
     */
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

    /**
     * Returns a list of books that has the provided title in its title name
     * O(n) time complexity - Has to check each book if it contains the given title
     * @param title, the title of the book, case-sensitive and partial matching
     * @return List<Book>, a list of books
     */
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

    /**
     * Searches through to find all books that contain the given author name
     * O(n) time complexity - Has to search through each book to see if it contains the name
     * @param author, the author name, can be case-sensitive and partial matching
     * @return List<Book>, a list of books that contains the author's name
     */
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

    /**
     * Finds books within the given price range of min and max
     * O(n) time complexity - Has to check each book to see if it is within that range
     * @param min, the minimum price
     * @param max, the maximum price
     * @return List<Book>, a list of books between the min and max price range
     */
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

    /**
     * Finds books that were released on the provided year
     * O(n) time complexity - Has to check all books if it were released on that year
     * @param year, the year the book was released on
     * @return List<Book>, a list of books that were released on the given year
     */
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

    /**
     * Checks how many books are in the bookstore
     * O(1) constant time complexity - only lookup needed, no searching
     * @return the size of the bookstore
     */
    public int size() {
        return this.books.size();
    }

    /**
     * Gets the inventory value of all the books inside the bookstore
     * O(n) time complexity - Has to go through all books to add its value to the total
     * @return inventoryValue, the value of all books combined
     */
    public double inventoryValue() {
        double inventoryValue = 0;

        for (Book currBook: books) {
            inventoryValue += currBook.getPrice();
        }

        return inventoryValue;
    }

    /**
     * Checks to see which is the most expensive book inside the bookstore
     * O(n) time complexity - Has to compare every book with its cost
     * @return Book, the most expensive book
     */
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

    /**
     * Gets the book that was released most recently
     * O(n) time complexity - Has to check and compare its release date
     * @return Book, the book that was released most recently
     */
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

    /**
     * Gets a defensive copy of the original bookstore as an array
     * O(n) time complexity - Has to loop over each book to copy over
     * @return Book[], a copy of the original bookstore in array format
     */
    public Book[] snapshotArray() {
        Book[] bookArray = new Book[this.size()];

        bookArray = this.books.toArray(bookArray);
        return bookArray;
    }

    /**
     * Gets a defensive copy of the original bookstore as a list
     * O(n) time complexity - Has to loop over each book to copy over
     * @return List<Book>, a copy of the original bookstore in a list format
     */
    public List<Book> getAllBooks() {
        ArrayList<Book> newBookList = new ArrayList<>();
        for (Book currBook: books) {
            newBookList.add(currBook);
        }

        return newBookList;
    }
}