package com.university.bookstore.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookTest {

    private final String validIsbn13 = "9780134685990";
    private final String validIsbn10 = "0134685997";
    private final String validTitle = "Effective Java";
    private final String validAuthor = "Joshua Bloch";
    private final double validPrice = 45.99;
    private final int validYear = 2026;

    //Constructor section
    @Test
    @Order(1)
    void testValidBookCreationISBN13() {
        Book book = new Book(validIsbn13, validTitle, validAuthor, validPrice, validYear);
        assertNotNull(book);
        assertEquals(validIsbn13, book.getIsbn());
        assertEquals(validTitle, book.getTitle());
        assertEquals(validAuthor, book.getAuthor());
        assertEquals(validPrice, book.getPrice());
        assertEquals(validYear, book.getYear());
    }

    @Test
    @Order(2)
    void testValidBookCreationISBN10() {
        Book book = new Book(validIsbn10, validTitle, validAuthor, validPrice, validYear);
        assertEquals(validIsbn10, book.getIsbn());
    }

    //ISBN section
    @Test
    @Order(3)
    void testISBNNullThrowsException() {
        assertThrows(NullPointerException.class,
                () -> new Book(null, validTitle, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(4)
    void testISBNEmptyThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("   ", validTitle, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(5)
    void testISBNTooShortThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("123456789", validTitle, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(6)
    void testISBNTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("12345678901234", validTitle, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(7)
    void testISBNWithNonDigitsThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("9780134685ABCD", validTitle, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(8)
    void testISBNWithHyphensIsTrimmed() {
        Book book = new Book("978-0-13-468599-0", validTitle, validAuthor, validPrice, validYear);
        assertEquals("9780134685990", book.getIsbn());
    }

    //Title and Author string section
    @Test
    @Order(9)
    void testTitleNullThrowsException() {
        assertThrows(NullPointerException.class,
                () -> new Book(validIsbn13, null, validAuthor, validPrice, validYear));
    }

    @Test
    @Order(10)
    void testAuthorNullThrowsException() {
        assertThrows(NullPointerException.class,
                () -> new Book(validIsbn13, validTitle, null, validPrice, validYear));
    }

    @Test
    @Order(11)
    void testTitleTrimmed() {
        Book book = new Book(validIsbn13, "  My Book  ", validAuthor, validPrice, validYear);
        assertEquals("My Book", book.getTitle());
    }

    @Test
    @Order(12)
    void testAuthorTrimmed() {
        Book book = new Book(validIsbn13, validTitle, "  Author  ", validPrice, validYear);
        assertEquals("Author", book.getAuthor());
    }

    //Price section
    @Test
    @Order(13)
    void testNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book(validIsbn13, validTitle, validAuthor, -1.0, validYear));
    }

    @Test
    @Order(14)
    void testZeroPriceValid() {
        Book book = new Book(validIsbn13, validTitle, validAuthor, 0.0, validYear);
        assertEquals(0.0, book.getPrice());
    }

    //Year section
    @Test
    @Order(15)
    void testYearTooEarlyThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book(validIsbn13, validTitle, validAuthor, validPrice, 1449));
    }

    @Test
    @Order(16)
    void testYearFutureTooFarThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book(validIsbn13, validTitle, validAuthor, validPrice, 2027));
    }

    @Test
    @Order(17)
    void testYearAtLowerBound() {
        Book book = new Book(validIsbn13, validTitle, validAuthor, validPrice, 1450);
        assertEquals(1450, book.getYear());
    }

    @Test
    @Order(18)
    void testYearAtUpperBound() {
        Book book = new Book(validIsbn13, validTitle, validAuthor, validPrice, 2026); // currentYear+1
        assertEquals(2026, book.getYear());
    }

    //Equals section
    @Test
    @Order(19)
    void testEqualsSameISBN() {
        Book book1 = new Book(validIsbn13, "Book1", "Author1", validPrice, validYear);
        Book book2 = new Book(validIsbn13, "Book2", "Author2", 10.0, 2020);
        assertTrue(book1.equals(book2));
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    @Order(20)
    void testNotEqualsDifferentISBN() {
        Book book1 = new Book(validIsbn13, validTitle, validAuthor, validPrice, validYear);
        Book book2 = new Book(validIsbn10, validTitle, validAuthor, validPrice, validYear);
        assertFalse(book1.equals(book2));
    }

    //Compare section
    @Test
    @Order(21)
    void testCompareToDifferentTitles() {
        Book a = new Book(validIsbn13, "A Title", validAuthor, validPrice, validYear);
        Book b = new Book(validIsbn10, "B Title", validAuthor, validPrice, validYear);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    @Order(22)
    void testCompareToSameTitles() {
        Book a = new Book(validIsbn13, "Same Title", validAuthor, validPrice, validYear);
        Book b = new Book(validIsbn10, "Same Title", validAuthor, validPrice, validYear);
        assertEquals(0, a.compareTo(b));
    }

    //ToString section
    @Test
    @Order(23)
    void testToStringContainsAllFields() {
        Book book = new Book(validIsbn13, validTitle, validAuthor, validPrice, validYear);
        String str = book.toString();
        assertTrue(str.contains(validTitle));
        assertTrue(str.contains(validAuthor));
        assertTrue(str.contains(String.valueOf(validPrice)));
        assertTrue(str.contains(String.valueOf(validYear)));
    }
}
