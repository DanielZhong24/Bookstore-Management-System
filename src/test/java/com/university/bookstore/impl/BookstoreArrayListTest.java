package com.university.bookstore.impl;
import com.university.bookstore.model.Book;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("BookstoreArrayList Tests")
class BookstoreArrayListTest {

    private BookstoreArrayList bookstore;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book duplicate;


    @BeforeEach
    void setUp() {
        bookstore = new BookstoreArrayList();
        book1 = new Book("9374859192843", "My book", "John Doe", 29.99, 2012);
        book2 = new Book("9375827462849", "Fahrenheit 451", "Ray Bradbury", 9.99, 2014);
        book3 = new Book("9576818375934", "Hunger Games", "Jane Smith", 10.99, 2015);
        book4 = new Book("9345834573845", "Amulet", "Samantha Smith", 12.99, 2005);
        duplicate = new Book("9374859192843", "My book", "John Doe", 29.99, 2012);
    }

    @Test
    @Order(1)
    @DisplayName("Create an empty bookstore")
    void createEmptyBookstore() {
        bookstore = new BookstoreArrayList();
        assertEquals(0, bookstore.size());
        assertEquals(0.0, bookstore.inventoryValue());
        assertNull(bookstore.getMostExpensive());
        assertNull(bookstore.getMostRecent());
        assertTrue(bookstore.getAllBooks().isEmpty());
        assertEquals(0, bookstore.snapshotArray().length);
    }

    @Test
    @Order(2)
    @DisplayName("Test with one book")
    void createOneBook() {
        assertTrue(bookstore.add(book1));
        assertEquals(1, bookstore.size());
        assertEquals(book1, bookstore.getMostRecent());
        assertEquals(book1, bookstore.getMostExpensive());
        assertEquals(book1, bookstore.getMostRecent());
        assertTrue(bookstore.getAllBooks().contains(book1));
        assertFalse(bookstore.getAllBooks().isEmpty());
        assertEquals(book1, bookstore.findByIsbn(book1.getIsbn()));
    }

    @Test
    @Order(3)
    @DisplayName("Test with Duplicate books")
    void createDuplicateBook() {
        assertTrue(bookstore.add(book1));
        assertFalse(bookstore.add(duplicate));
        assertEquals(1, bookstore.size());
        assertEquals(book1, bookstore.findByIsbn(book1.getIsbn()));
        assertEquals("My book", bookstore.findByIsbn(book1.getIsbn()).getTitle());
    }

    @Test
    @Order(4)
    @DisplayName("Search with no title in bookstore")
    void searchNoResults() {
        bookstore.add(book1);
        List<Book> output = bookstore.findByTitle(book2.getTitle());
        assertEquals(0, output.size());
        assertNotNull(output); // Supposed to return an empty object instead of null
    }

    @Test
    @Order(5)
    @DisplayName("Search with no valid author in bookstore")
    void searchInvalidAuthor() {
        bookstore.add(book2);
        List<Book> output = bookstore.findByAuthor(book3.getAuthor());
        assertEquals(0, output.size());
        assertNotNull(output);
        assertEquals("Ray Bradbury", bookstore.findByIsbn(book2.getIsbn()).getAuthor());
    }

    @Test
    @Order(6)
    @DisplayName("Search with invalid year")
    void searchInvalidYear() {
        bookstore.add(book4);
        List<Book> output = bookstore.findByYear(book2.getYear());
        assertEquals(0, output.size());
        assertNotNull(output);
        assertEquals(2005, bookstore.findByIsbn(book4.getIsbn()).getYear());
    }
}

