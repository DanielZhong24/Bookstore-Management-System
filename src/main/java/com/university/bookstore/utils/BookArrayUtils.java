package com.university.bookstore.utils;

import com.university.bookstore.model.Book;
import java.util.Arrays;

/**
 * Utility class for array-based book operations.
 * Provides static methods for counting, filtering, sorting,
 * statistics, and array manipulation.
 *
 * <p>Null inputs and null elements are handled gracefully.</p>
 *
 * <p>All methods are static; the class cannot be instantiated.</p>
 *
 * @author Shrey Patel
 * @version 1.1
 * @since 2025-09-18
 */
public final class BookArrayUtils {

    /** Private constructor to prevent instantiation. */
    private BookArrayUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /** Counts books published before a given year. */
    public static int countBeforeYear(Book[] books, int yearCutoff) {
        if (books == null) return 0;
        int count = 0;
        for (Book b : books) {
            if (b != null && b.getYear() < yearCutoff) {
                count++;
            }
        }
        return count;
    }

    /** Counts books by a specific author. */
    public static int countByAuthor(Book[] books, String author) {
        if (books == null || author == null) return 0;
        int count = 0;
        for (Book b : books) {
            if (b != null && author.equals(b.getAuthor())) {
                count++;
            }
        }
        return count;
    }

    /** Returns books with price at most maxPrice. */
    public static Book[] filterPriceAtMost(Book[] books, double maxPrice) {
        if (books == null) return new Book[0];
        int count = 0;
        for (Book b : books) {
            if (b != null && b.getPrice() <= maxPrice) count++;
        }
        Book[] result = new Book[count];
        int index = 0;
        for (Book b : books) {
            if (b != null && b.getPrice() <= maxPrice) result[index++] = b;
        }
        return result;
    }

    /** Returns books published in a specific decade. */
    public static Book[] filterByDecade(Book[] books, int decade) {
        if (books == null) return new Book[0];
        int count = 0;
        for (Book b : books) {
            if (b != null && b.getYear() >= decade && b.getYear() <= decade + 9) count++;
        }
        Book[] result = new Book[count];
        int index = 0;
        for (Book b : books) {
            if (b != null && b.getYear() >= decade && b.getYear() <= decade + 9) result[index++] = b;
        }
        return result;
    }

    /** Sorts books by price in ascending order (in-place). Nulls go to the end. */
    public static void sortByPrice(Book[] books) {
        if (books == null) return;
        Arrays.sort(books, (b1, b2) -> {
            if (b1 == null && b2 == null) return 0;
            if (b1 == null) return 1;
            if (b2 == null) return -1;
            return Double.compare(b1.getPrice(), b2.getPrice());
        });
    }

    /** Sorts books by year in ascending order (in-place). Nulls go to the end. */
    public static void sortByYear(Book[] books) {
        if (books == null) return;
        Arrays.sort(books, (b1, b2) -> {
            if (b1 == null && b2 == null) return 0;
            if (b1 == null) return 1;
            if (b2 == null) return -1;
            return Integer.compare(b1.getYear(), b2.getYear());
        });
    }

    /** Returns the average price of all books. */
    public static double averagePrice(Book[] books) {
        if (books == null) return 0.0;
        double sum = 0.0;
        int count = 0;
        for (Book b : books) {
            if (b != null) {
                sum += b.getPrice();
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    /** Finds the oldest book in the array. */
    public static Book findOldest(Book[] books) {
        if (books == null) return null;
        Book oldest = null;
        for (Book b : books) {
            if (b != null && (oldest == null || b.getYear() < oldest.getYear())) oldest = b;
        }
        return oldest;
    }

    /** Merges two book arrays into one. Null arrays are treated as empty. */
    public static Book[] merge(Book[] arr1, Book[] arr2) {
        int len1 = (arr1 == null) ? 0 : arr1.length;
        int len2 = (arr2 == null) ? 0 : arr2.length;
        Book[] merged = new Book[len1 + len2];
        int index = 0;
        if (arr1 != null) for (Book b : arr1) merged[index++] = b;
        if (arr2 != null) for (Book b : arr2) merged[index++] = b;
        return merged;
    }

    /** Removes duplicate books based on ISBN using nested loops. */
    public static Book[] removeDuplicates(Book[] books) {
        if (books == null) return new Book[0];

        Book[] temp = new Book[books.length];
        int count = 0;

        for (Book b : books) {
            if (b == null) continue;

            boolean duplicate = false;
            for (int i = 0; i < count; i++) {
                if (b.getIsbn().equals(temp[i].getIsbn())) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                temp[count++] = b;
            }
        }

        Book[] result = new Book[count];
        for (int i = 0; i < count; i++) result[i] = temp[i];
        return result;
    }
}
