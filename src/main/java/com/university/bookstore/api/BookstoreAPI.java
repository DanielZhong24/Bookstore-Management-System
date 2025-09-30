package com.university.bookstore.api;
import java.util.List;

import com.university.bookstore.model.Book;
/**
*This code defines a contract for the API,
* the API provides search capabilities, analytics, and export operations.
*/
public interface BookstoreAPI {
    /**
    *a book is added to the bookstore inventory.
    *
    *@param book the Book to add
    *@return {@code true} if the book was successfully add, {@code false} otherwise
    */
    boolean add(Book book);
    /**
    *will remove a book from the bookstore inventory using its ISBN
    *
    *@param isbn the ISBN of the book to remove
    *@return {@code true} if the book was found and removed, {@code false} if it isn't found and removed
    */
    boolean removeByIsbn(String isbn);
    /**
    *will locate the book by its ISBN
    *@param isbn the ISBN to search for
    *@return the Book objects, or an empty list if none are found
    */
    Book findByIsbn(String isbn);
    /**
    *finds all books that have titles matching the query given
    *@param titleQuery the tile or partial title to search for
    *@return a list of matching Book objects, or an empty list if none are found
    */
    List<Book> findByTitle(String titleQuery);
    /**
    *locates a book by the author's name
    *@param authorQuery the author's name or part of their name
    *@return a link of matching Book objects, or an empty list if none are found
    */
    List<Book> findByAuthor(String authorQuery);
    /**
    *locates all books in a certain price range
    *@param minPrice the lowest price
    *@param maxPrice the highest price
    *@return a list of Book objects within the specified range, or empty list if none are found
    */
    List<Book> findByPriceRange(double minPrice, double maxPrice);
        /**
    *locates all books that were published during a specified year
    *@param year the publication year
    @return a list of Book objects that were published during that year or an empty list if there are none
    */
    List<Book> findByYear(int year);
    /** 
    * will return the total number of books in the inventory
    *@return the number of books
    */
    int size();
    /**
    *Will get the most expensive book currently in the inventory
    *@return the Book with the highest price, or {@code null} if the inventory is empty
    **/
    double inventoryValue();
    /**
    * Gets the most expensive book in the inventory
    *@return the Book with the most recent publication year, or {@code null} if the inventory is empty
    */
    Book getMostExpensive();
    /**
    *gets the most recently published bood in the inventory
    *@return the Book with the most recent publication year, or {@code null} if the inventory is empty
    */
    Book getMostRecent();

    // Export operations
    /**
    * Will create a snapshot of the books currently contained in the inventory as a list
    *@return an array containinng all books in the inventory
    */
    Book[] snapshotArray();
    /**
    *Gets all the books that are currently in the inventory as a list
    *@return a list containing all Book objects
    */
    List<Book> getAllBooks();

}
