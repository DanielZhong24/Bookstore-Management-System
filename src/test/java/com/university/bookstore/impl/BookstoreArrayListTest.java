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
    private Book book5;
    private Book book6;
    private Book duplicate;


    @BeforeEach
    void setUp() {
        bookstore = new BookstoreArrayList();
        book1 = new Book("9374859192843", "My book", "John Doe", 29.99, 2012);
        book2 = new Book("9375827462849", "Fahrenheit 451", "Ray Bradbury", 9.99, 2014);
        book3 = new Book("9576818375934", "Hunger Games", "Jane Smith", 10.99, 2015);
        book4 = new Book("9345834573845", "Amulet", "Samantha Smith", 12.99, 2005);
        duplicate = new Book("9374859192843", "My book", "John Doe", 29.99, 2012);
        book5 = new Book("9365810375869", "Amulet Stone", "Jane Doe", 10.99, 2008);
        book6 = new Book("9384758393475", "Hunger Games Sequel", "Jane Doe", 45.99, 2015);
    }

    @Test
    @Order(1)
    @DisplayName("Create an empty bookstore")
    void testCreateEmptyBookstore() {
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
    void testCreateOneBook() {
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
    void testCreateDuplicateBook() {
        assertTrue(bookstore.add(book1));
        assertFalse(bookstore.add(duplicate));
        assertEquals(1, bookstore.size());
        assertEquals(book1, bookstore.findByIsbn(book1.getIsbn()));
        assertEquals("My book", bookstore.findByIsbn(book1.getIsbn()).getTitle());
    }

    @Test
    @Order(4)
    @DisplayName("Search with no title in bookstore")
    void testSearchNoResults() {
        bookstore.add(book1);
        List<Book> output = bookstore.findByTitle(book2.getTitle());
        assertEquals(0, output.size());
        assertNotNull(output); // Supposed to return an empty object instead of null
        assertNull(bookstore.findByTitle("  "));
        assertNull(bookstore.findByTitle(null));
    }

    @Test
    @Order(5)
    @DisplayName("Search with no valid author in bookstore")
    void testSearchInvalidAuthor() {
        bookstore.add(book2);
        List<Book> output = bookstore.findByAuthor(book3.getAuthor());
        assertEquals(0, output.size());
        assertNotNull(output);
        assertEquals("Ray Bradbury", bookstore.findByIsbn(book2.getIsbn()).getAuthor());
        assertNull(bookstore.findByAuthor(null));
        assertNull(bookstore.findByAuthor("       "));
    }

    @Test
    @Order(6)
    @DisplayName("Search with invalid year")
    void testSearchInvalidYear() {
        bookstore.add(book4);
        List<Book> output = bookstore.findByYear(book2.getYear());
        assertEquals(0, output.size());
        assertNotNull(output);
        assertEquals(2005, bookstore.findByIsbn(book4.getIsbn()).getYear());
        assertNull(bookstore.findByYear(-2));
        assertNull(bookstore.findByYear(2026));

    }

    @Test
    @Order(7)
    @DisplayName("Search with invalid ISBN")
    void testSearchInvalidISBN() {
        bookstore.add(book4);
        assertNull(bookstore.findByIsbn(book3.getIsbn()));
        assertEquals(1, bookstore.size());
        assertEquals("9345834573845", bookstore.findByIsbn(book4.getIsbn()).getIsbn());
        assertNull(bookstore.findByIsbn(null));
        assertNull(bookstore.findByIsbn("        "));
    }

    @Test
    @Order(8)
    @DisplayName("Multiple books - find by title")
    void testMultiFindByTitle() {
        bookstore.add(book1);
        bookstore.add(book2);
        bookstore.add(book3);
        bookstore.add(book4);
        bookstore.add(book5);
        bookstore.add(book6);

        List<Book> amuletBooks = bookstore.findByTitle("Amulet");
        assertEquals(2, amuletBooks.size());
        assertTrue(amuletBooks.contains(book4));
        assertTrue(amuletBooks.contains(book5));

        List<Book> hungerBooks = bookstore.findByTitle("Hunger Games");
        assertEquals(2, hungerBooks.size());
        assertTrue(hungerBooks.contains(book6));
        assertTrue(hungerBooks.contains(book3));

        List<Book> myBook = bookstore.findByTitle("Book");
        assertEquals(1, myBook.size());
        assertTrue(myBook.contains(book1));
    }

    @Test
    @Order(9)
    @DisplayName("Multiple books - find by author")
    void testMultiFindByAuthor() {
        bookstore.add(book1);
        bookstore.add(book2);
        bookstore.add(book3);
        bookstore.add(book4);
        bookstore.add(book5);
        bookstore.add(book6);

        List<Book> janeBooks = bookstore.findByAuthor("Jane");
        assertEquals(3, janeBooks.size());
        assertTrue(janeBooks.contains(book3));
        assertTrue(janeBooks.contains(book5));
        assertTrue(janeBooks.contains(book6));

        List<Book> smithLastNameBooks = bookstore.findByAuthor("Smith");
        assertEquals(2, smithLastNameBooks.size());
        assertTrue(smithLastNameBooks.contains(book3));
        assertTrue(smithLastNameBooks.contains(book4));
    }

    @Test
    @Order(10)
    @DisplayName("Multiple books - find by price range")
    void testMultiFindByPriceRange() {
        bookstore.add(book1); // 29.99
        bookstore.add(book2); // 9.99
        bookstore.add(book3); // 10.99
        bookstore.add(book4); // 12.99
        bookstore.add(book5); // 10.99
        bookstore.add(book6); // 45.99

        List<Book> priceRange1 = bookstore.findByPriceRange(10, 15);
        assertEquals(3, priceRange1.size());
        assertTrue(priceRange1.contains(book3));
        assertTrue(priceRange1.contains(book4));
        assertTrue(priceRange1.contains(book5));

        List<Book> priceRange2 = bookstore.findByPriceRange(20, 50);
        assertEquals(2, priceRange2.size());
        assertTrue(priceRange2.contains(book1));
        assertTrue(priceRange2.contains(book6));

        List<Book> priceRange3 = bookstore.findByPriceRange(9.99, 9.99);
        assertEquals(1, priceRange3.size());
        assertTrue(priceRange3.contains(book2));
    }

    @Test
    @Order(11)
    @DisplayName("Multiple books - find by year")
    void testMultiFindByYear() {
        bookstore.add(book1); // 2012
        bookstore.add(book3); // 2015
        bookstore.add(book6); // 2015

        assertNull(bookstore.findByYear(-200));
        List<Book> yearBook1 = bookstore.findByYear(2012);
        assertEquals(1, yearBook1.size());
        assertTrue(yearBook1.contains(book1));

        List<Book> yearBook2 = bookstore.findByYear(2015);
        assertEquals(2, yearBook2.size());
        assertTrue(yearBook2.contains(book3));
        assertTrue(yearBook2.contains(book6));
    }

    @Test
    @Order(12)
    @DisplayName("Invalid prices")
    void testInvalidPrices() {
        assertThrows(IllegalArgumentException.class, () -> bookstore.findByPriceRange(-1, 220));
        assertThrows(IllegalArgumentException.class, () -> bookstore.findByPriceRange(0, -1));
        assertThrows(IllegalArgumentException.class, () -> bookstore.findByPriceRange(0, 0));
        assertThrows(IllegalArgumentException.class, () -> bookstore.findByPriceRange(50, 20));
    }

    @Test
    @Order(13)
    @DisplayName("Test Inventory Value")
    void testInventoryValue() {
        assertEquals(0.0, bookstore.inventoryValue(), 0.01);
        bookstore.add(book1);
        assertEquals(29.99, bookstore.inventoryValue(), 0.01);
        bookstore.add(book2);
        assertEquals(39.98, bookstore.inventoryValue(), 0.01);
        bookstore.add(book3);
        assertEquals(50.97, bookstore.inventoryValue(), 0.01);

        bookstore.removeByIsbn(book1.getIsbn());
        assertEquals(20.98, bookstore.inventoryValue(), 0.01);

        assertEquals(2, bookstore.size());
    }

    @Test
    @Order(14)
    @DisplayName("Remove Book edge case")
    void testRemoveEdgeCase() {
        bookstore.add(book1);
        bookstore.removeByIsbn("      ");
        assertEquals(1, bookstore.size());
        bookstore.removeByIsbn(null);
        assertEquals(1, bookstore.size());
        assertTrue(bookstore.getAllBooks().contains(book1));
    }

    @Test
    @Order(15)
    @DisplayName("Remove Book")
    void testRemoveByIsbn() {
        bookstore.add(book1);
        bookstore.add(book2);
        bookstore.add(book3);

        assertEquals(3, bookstore.size());

        bookstore.removeByIsbn(book1.getIsbn());
        assertEquals(2, bookstore.size());
        assertFalse(bookstore.getAllBooks().contains(book1));
        assertTrue(bookstore.getAllBooks().contains(book2));
        assertTrue(bookstore.getAllBooks().contains(book3));
    }

    @Test
    @Order(16)
    @DisplayName("Get most expensive book")
    void testGetMostExpensive() {
        assertNull(bookstore.getMostExpensive());

        bookstore.add(book2);
        assertEquals(book2, bookstore.getMostExpensive());
        bookstore.add(book3);
        bookstore.add(book4);
        bookstore.add(book1);
        assertEquals(book1, bookstore.getMostExpensive());
        bookstore.add(book6);
        assertEquals(book6, bookstore.getMostExpensive());
    }

    @Test
    @Order(17)
    @DisplayName("Get most recent book")
    void testGetMostRecent() {
        assertNull(bookstore.getMostRecent());

        bookstore.add(book1);
        assertEquals(book1, bookstore.getMostRecent());

        bookstore.add(book4);
        assertEquals(book1, bookstore.getMostRecent());
        bookstore.add(book6);
        assertEquals(book6, bookstore.getMostRecent());
    }

    @Test
    @Order(18)
    @DisplayName("Return a defensive copy as an array")
    void testDefensiveArrayCopy() {
        bookstore.add(book1);
        bookstore.add(book2);
        bookstore.add(book3);

        Book[] snapshot1 = bookstore.snapshotArray();
        assertEquals(3, snapshot1.length);

        snapshot1[0] = book4;

        Book[] snapshot2 = bookstore.snapshotArray();
        assertEquals(book1, snapshot2[0]);
        assertNotEquals(book4, snapshot2[0]);
    }

    @Test
    @Order(19)
    @DisplayName("Return a defensive copy as a list")
    void testDefensiveArrayListCopy() {
        bookstore.add(book1);
        bookstore.add(book2);
        bookstore.add(book3);

        List<Book> list = bookstore.getAllBooks();
        assertEquals(3, list.size());

        list.add(book4);
        list.add(book5);

        List<Book> list2 = bookstore.getAllBooks();
        assertEquals(3, list2.size());
    }
}

