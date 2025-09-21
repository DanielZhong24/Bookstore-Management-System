package com.university.bookstore.impl;
import com.university.bookstore.model.Book;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("BookstoreArrayList Tests")
class BookstoreArrayListTest {

    private BookstoreArrayList bookstore;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;


    @BeforeEach
    void setUp() {
        bookstore = new BookstoreArrayList();
        book1 = new Book("9374859192843", "My book", "John Doe", 29.99, 2012);
        book2 = new Book("9375827462849", "Fahrenheit 451", "Ray Bradbury", 9.99, 2014);
        book3 = new Book("9576818375934", "Hunger Games", "Jane Smith", 10.99, 2015);
        book4 = new Book("9345834573845", "Amulet", "Samantha Smith", 12.99, 2005);
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
}

