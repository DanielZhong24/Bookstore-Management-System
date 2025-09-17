package com.university.bookstore.model;


/**
 *
 */
public final class Book implements  Comparable<Book>{

    /**
     *
     */
    private final String isbn;
    private final String title;
    private final String author;
    private final double price;
    private final int year;

    /**
     *
     * @param isbn a 10 or 13 length ISBN code
     * @param title the title of the book
     * @param author name of the author of the book
     * @param price price of the book
     * @param year the year the book was published
     */
    public Book(String isbn, String title, String author, double price, int year) {
        this.isbn = isValidISBN(isbn);
        this.title = isValidString(title);
        this.author = isValidString(author);
        this.price = isValidPrice(price);
        this.year = isValidYear(year);
    }

    /**
     * Checks if the isbn provided is valid.
     * – Must be non-null and non-blank (not empty or just whitespace)
     * – Must be exactly 13 digits (modern books) or 10 digits (older books)
     * @param isbn a 10 or 13 length ISBN code
     * @return the validated, properly trimmed ISBN
     */
    private String isValidISBN(String isbn){
        if(isbn == null){
            throw new NullPointerException("ISBN is either empty or null");
        }

        String trimmed = isbn.replace("-","").trim();

        if(trimmed.length() > 13 || trimmed.length() < 10){
            throw new IllegalArgumentException("Invalid length for ISBN");
        }

        if(!this.onlyDigits(trimmed)){
            throw new IllegalArgumentException("Invalid characters for ISBN");
        }

        return trimmed;
    }

    /**
     * Checks if the string contains only integers
     * @param s a string for testing whether it contains only integers
     * @return a boolean
     */
    private boolean onlyDigits(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {

                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the author or book name is valid
     * – Must be non-null and non-blank
     * – Should contain meaningful text (not just spaces)
     * @param test a String that is used to check if it is a valid input
     * @return a valid string
     */
    private String isValidString(String test){
        if(test == null){
            throw new NullPointerException("String is either empty or null");
        }
        test = test.trim();

        return test;
    }

    /**
     * Checks if the price is valid
     * Must be non-negative (≥ 0.0)
     * @param price the price of the book
     * @return the valid price
     */
    public double isValidPrice(double price){
        if(price < 0){
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        return price;
    }

    /**
     * Checks if the year is valid
     * - Must be between 1450 (invention of printing press) and current year + 1
     * – Current year + 1 allows for pre-orders of upcoming books
     * @param year the publishing year of the book
     * @return the valid year
     */
    public int isValidYear(int year){
        int currentYear = 2025;
        if(year < 1450 || year > currentYear +1){
            throw new IllegalArgumentException("Invalid year");
        }
        return year;
    }


    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN as a String
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author's name as a String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the price of the book.
     *
     * @return the price as a double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the publication year of the book.
     *
     * @return the year as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Compares this book to another book based on title.
     *
     * @param o the other Book to compare to
     * @return a negative integer, zero, or a positive integer as this title
     *         is less than, equal to, or greater than the specified title
     */
    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }

    /**
     * Checks if this book is equal to another book based on hash code.
     *
     * @param o the other Book to compare to
     * @return true if the hash codes are equal, false otherwise
     */
    public boolean equals(Book o) {
        return this.hashCode() == o.hashCode();
    }

    /**
     * Returns the hash code for this book, based on its ISBN.
     *
     * @return the hash code as an int
     */
    public int hashCode() {
        return this.isbn.hashCode();
    }

    /**
     * Returns a string representation of the book.
     *
     * @return a formatted string with title, author, price, and year
     */
    public String toString() {
        return String.format("{Title: %s, Author: %s, Price: %.2f, Year: %d}", title, author, price, year);
    }
}