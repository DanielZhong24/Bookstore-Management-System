package com.university.bookstore.model;

import org.junit.jupiter.api.*;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Book Class Tests")
class BookTest {

    private Book validBook;
    private final String VALID_ISBN_13 = "9780134685990";
    private final String VALID_ISBN_10 = "0134685997";

    @BeforeEach
    void setUp() {
        validBook = new Book(VALID_ISBN_13, "How to read a book", "Daniel Zhong", 29.99, 2025);
    }

    @Test
    @Order(1)
    @DisplayName("Should create book with valid parameters")
    void testValidBookCreation() {
        assertNotNull(validBook);
        assertEquals(VALID_ISBN_13, validBook.getIsbn());
        assertEquals("Daniel Zhong", validBook.getAuthor());
        assertEquals("How to read a book", validBook.getTitle());
        assertEquals(29.99, validBook.getPrice());
        assertEquals(2025, validBook.getYear());

    }
}