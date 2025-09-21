package com.university.bookstore.utils;

import com.university.bookstore.model.Book;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookArrayUtilsExtraTest {

    private Book b1, b2, b3, b4, b5;

    @BeforeEach
    void setUp() {
        b1 = new Book("1111111111111", "Alpha", "AuthorA", 20.0, 1999);
        b2 = new Book("2222222222222", "Beta", "AuthorB", 35.0, 2005);
        b3 = new Book("3333333333333", "Gamma", "AuthorA", 35.0, 2005);
        b4 = new Book("4444444444444", "Delta", "AuthorC", 50.0, 2010);
        b5 = new Book("5555555555555", "Epsilon", "AuthorC", 50.0, 2010);
    }

    // ======= Counting Edge Cases =======

    @Test
    @Order(1)
    void testCountBeforeYearAllMatch() {
        Book[] arr = {b1, b2, b3};
        int count = BookArrayUtils.countBeforeYear(arr, 2010);
        assertEquals(3, count); // all books before 2010
    }

    @Test
    @Order(2)
    void testCountByAuthorNoMatch() {
        Book[] arr = {b1, b2, b3};
        int count = BookArrayUtils.countByAuthor(arr, "Unknown");
        assertEquals(0, count);
    }

    // ======= Filtering Edge Cases =======

    @Test
    @Order(3)
    void testFilterPriceAtMostAllMatch() {
        Book[] arr = {b1, b2, b3};
        Book[] filtered = BookArrayUtils.filterPriceAtMost(arr, 40.0);
        assertEquals(3, filtered.length);
    }

    @Test
    @Order(4)
    void testFilterByDecadeMultipleDecades() {
        Book[] arr = {b1, b2, b3, b4, b5};
        Book[] filtered2000s = BookArrayUtils.filterByDecade(arr, 2000);
        assertEquals(2, filtered2000s.length); // b2, b3
        Book[] filtered2010s = BookArrayUtils.filterByDecade(arr, 2010);
        assertEquals(2, filtered2010s.length); // b4, b5
    }

    // ======= Sorting Edge Cases =======

    @Test
    @Order(5)
    void testSortByPriceTies() {
        Book[] arr = {b2, b3, b1};
        BookArrayUtils.sortByPrice(arr);
        assertEquals(b1, arr[0]);
        assertTrue((arr[1] == b2 && arr[2] == b3) || (arr[1] == b3 && arr[2] == b2));
    }

    @Test
    @Order(6)
    void testSortByYearTies() {
        Book[] arr = {b2, b3, b1};
        BookArrayUtils.sortByYear(arr);
        assertEquals(b1, arr[0]);
        assertTrue((arr[1] == b2 && arr[2] == b3) || (arr[1] == b3 && arr[2] == b2));
    }

    // ======= Statistics Edge Cases =======

    @Test
    @Order(7)
    void testAveragePriceWithDuplicates() {
        Book[] arr = {b2, b3, b4, b5};
        double avg = BookArrayUtils.averagePrice(arr);
        assertEquals((35.0 + 35.0 + 50.0 + 50.0) / 4, avg);
    }

    @Test
    @Order(8)
    void testFindOldestWithTies() {
        Book[] arr = {b1, b2, b3};
        Book oldest = BookArrayUtils.findOldest(arr);
        assertEquals(b1, oldest);
    }

    // ======= Array Manipulation Edge Cases =======

    @Test
    @Order(9)
    void testMergeWithEmptyArray() {
        Book[] merged = BookArrayUtils.merge(new Book[0], new Book[]{b1});
        assertEquals(1, merged.length);
        assertEquals(b1, merged[0]);
    }

    @Test
    @Order(10)
    void testRemoveDuplicatesNoDupes() {
        Book[] arr = {b1, b2, b3};
        Book[] unique = BookArrayUtils.removeDuplicates(arr);
        assertEquals(3, unique.length);
        assertEquals(b1, unique[0]);
        assertEquals(b2, unique[1]);
        assertEquals(b3, unique[2]);
    }

    // ======= Null and Edge Cases =======

    @Test
    @Order(11)
    void testNullArrayHandling() {
        assertEquals(0, BookArrayUtils.countBeforeYear(null, 2000));
        assertEquals(0, BookArrayUtils.countByAuthor(null, "AuthorA"));
        assertEquals(0, BookArrayUtils.filterPriceAtMost(null, 50).length);
        assertEquals(0, BookArrayUtils.filterByDecade(null, 2000).length);
        assertNull(BookArrayUtils.findOldest(null));
        assertEquals(0.0, BookArrayUtils.averagePrice(null));
        assertEquals(0, BookArrayUtils.removeDuplicates(null).length);
        BookArrayUtils.sortByPrice(null); // should not throw
        BookArrayUtils.sortByYear(null);  // should not throw
        Book[] merged = BookArrayUtils.merge(null, null);
        assertEquals(0, merged.length);
    }

    @Test
    @Order(12)
    void testArrayWithNullElements() {
        Book[] arr = {b1, null, b2, null, b3};
        BookArrayUtils.sortByPrice(arr);
        assertEquals(b1, arr[0]);
        assertEquals(b2, arr[1]);
        assertEquals(b3, arr[2]);
        assertNull(arr[3]);
        assertNull(arr[4]);

        Book[] filtered = BookArrayUtils.filterPriceAtMost(arr, 50);
        assertEquals(3, filtered.length);
    }
}
