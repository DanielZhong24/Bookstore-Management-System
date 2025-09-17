package com.university.bookstore.model;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Book{


    private final String isbn;
    private final String title;
    private final String author;
    private final double price;
    private final int year;

    private final int currentYear = 2025;

    public Book(String isbn, String title, String author, double price, int year) {
        this.isbn = isValidISBN(isbn);
        this.title = isValidString(title);
        this.author = isValidString(author);
        this.price = isValidPrice(price);
        this.year = isValidYear(year);
    }

    private String isValidISBN(String isbn){
        if(isbn == null){
            throw new NullPointerException("ISBN is either empty or null");
        }

        String trimmed = isbn.replace("-","").trim();

        if(trimmed.length() > 13 || trimmed.length() < 10){
            throw new IllegalArgumentException("Invalid length for ISBN");
        }

        try {
            int checkInteger = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid character in ISBN");
        }
        return trimmed;
    }

    private String isValidString(String test){
        if(test == null){
            throw new NullPointerException("String is either empty or null");
        }
        test = test.trim();

        return test;
    }

    public double isValidPrice(double price){
        if(price <= 0){
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        return price;
    }

    public int isValidYear(int year){
        if(year < 1450 || year > currentYear+1){
            throw new IllegalArgumentException("Invalid year");
        }
        return year;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}