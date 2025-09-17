package com.university.bookstore.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BookTest{
    @Test
    void testISBN() {
        Book book = new book("1234567890","TEST","TEST",29.99,2025);
        String isbn = book.getIsbn();
        assertEquals("1234567890", isbn, "The isbn should be the same");
    }
}