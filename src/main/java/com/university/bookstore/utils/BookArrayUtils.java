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

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException always, since this class
     *                                       should not be instantiated
     */
    private BookArrayUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Counts the number of books published before a given cutoff year.
     *
     * @param books      the array of books (may be null)
     * @param yearCutoff the cutoff year
     * @return the number of books published before {@code yearCutoff},
     *         or 0 if {@code books} is null
     */
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

    /**
     * Counts the number of books written by a specific author.
     *
     * @param books  the array of books (may be null)
     * @param author the author's name (must not be null)
     * @return the number of books by the specified author,
     *         or 0 if {@code books} or {@code author} is null
     */
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

    /**
     * Filters books with a price less than or equal to the specified maximum.
     *
     * @param books    the array of books (may be null)
     * @param maxPrice the maximum allowed price
     * @return a new array of books with price at most {@code maxPrice},
     *         or an empty array if {@code books} is null
     */
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

    /**
     * Filters books published in a specific decade.
     *
     * @param books  the array of books (may be null)
     * @param decade the starting year of the decade (e.g., 1990 for 1990–1999)
     * @return a new array of books published in the given decade,
     *         or an empty array if {@code books} is null
     */
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

    /**
     * Sorts the array of books by price in ascending order.
     * Null values are placed at the end.
     * <p>The input array is modified in place.</p>
     *
     * @param books the array of books (may be null)
     */
    public static void sortByPrice(Book[] books) {
        if (books == null) return;
        Arrays.sort(books, (b1, b2) -> {
            if (b1 == null && b2 == null) return 0;
            if (b1 == null) return 1;
            if (b2 == null) return -1;
            return Double.compare(b1.getPrice(), b2.getPrice());
        });
    }

    /**
     * Sorts the array of books by year in ascending order.
     * Null values are placed at the end.
     * <p>The input array is modified in place.</p>
     *
     * @param books the array of books (may be null)
     */
    public static void sortByYear(Book[] books) {
        if (books == null) return;
        Arrays.sort(books, (b1, b2) -> {
            if (b1 == null && b2 == null) return 0;
            if (b1 == null) return 1;
            if (b2 == null) return -1;
            return Integer.compare(b1.getYear(), b2.getYear());
        });
    }

    /**
     * Calculates the average price of all non-null books in the array.
     *
     * @param books the array of books (may be null)
     * @return the average price, or 0.0 if {@code books} is null
     *         or contains no non-null entries
     */
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

    /**
     * Finds the oldest (earliest published) non-null book in the array.
     *
     * @param books the array of books (may be null)
     * @return the oldest book, or {@code null} if {@code books} is null
     *         or contains only null entries
     */
    public static Book findOldest(Book[] books) {
        if (books == null) return null;
        Book oldest = null;
        for (Book b : books) {
            if (b != null && (oldest == null || b.getYear() < oldest.getYear())) oldest = b;
        }
        return oldest;
    }

    /**
     * Merges two arrays of books into a single array.
     * Null arrays are treated as empty.
     *
     * @param arr1 the first array of books (may be null)
     * @param arr2 the second array of books (may be null)
     * @return a new merged array containing all elements of {@code arr1} and {@code arr2}
     */
    public static Book[] merge(Book[] arr1, Book[] arr2) {
        int len1 = (arr1 == null) ? 0 : arr1.length;
        int len2 = (arr2 == null) ? 0 : arr2.length;
        Book[] merged = new Book[len1 + len2];
        int index = 0;
        if (arr1 != null) for (Book b : arr1) merged[index++] = b;
        if (arr2 != null) for (Book b : arr2) merged[index++] = b;
        return merged;
    }

    /**
     * Removes duplicate books from an array, based on ISBN equality.
     * Only the first occurrence of each ISBN is kept.
     *
     * @param books the array of books (may be null)
     * @return a new array containing only unique books,
     *         or an empty array if {@code books} is null
     */
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
