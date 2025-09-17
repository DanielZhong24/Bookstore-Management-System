import java.util.ArrayList;

public class BookstoreArrayList implements BookstoreAPI {
    private final List<Book> books;

    public BookstoreArrayList() {
        this.books = new ArrayList<>();
    }

    public boolean add(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        for (Book currBook : books) {
            if (currBook.getIsbn().equals(book.getIsbn())) {
                return false;
            }
        }

        return books.add(book);
    }


    public Book findByIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN is required");
        }

        for (Book currBook: books) {
            if (currBook.getIsbn().equals(isbn)) {
                return currBook;
            }
        }

        return null;
    }

    public boolean removeByIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN is required");
        }

        for (Book currBook: books) {
            if (currBook.getIsbn().equals(isbn)) {
                books.remove(currBook);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Book> findByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<>();
        title = title.trim().toLowerCase();

        for (Book currBook: books) {
            String bookTitle = currBook.getTitle().trim().toLowerCase();
            if (bookTitle.contains(title)) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }

    public ArrayList<Book> findByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return null;
        }

        author = author.toLowerCase();

        ArrayList<Book> bookList = new ArrayList<>();

        for (Book currBook: books) {
            String currAuthor = currBook.getAuthor().toLowerCase();
            if (currAuthor.contains(author)) {
                bookList.add(currAuthor);
            }
        }

        return bookList;
    }

    public ArrayList<Book> findByPriceRange(double min, double max) {
        if (min > max) {
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<>();

        for (Book currBook: books) {
            if (currBook.getPrice() >= min && currBook.getPrice() <= max) {
                bookList.add(currBook)
            }
        }

        return bookList
    }

    public ArrayList<Book> findByYear(int year) {
        if (year <= 0) {
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<>();
        for (Book currBook: books) {
            if (currBook.getYear() == year) {
                bookList.add(currBook);
            }
        }

        return bookList;
    }


    public int size() {
        return this.books.size();
    }

    public double inventoryValue() {
        double inventoryValue = 0;

        for (Book currBook: books) {
            inventoryValue += currBook.getPrice();
        }

        return inventoryValue;
    }

    public Book getMostExpensive() {
        Book mostExpensive = null;
        double maxPrice = 0;

        for (Book currBook: books) {
            if (currBook.getPrice() >= maxPrice) {
                maxPrice = currBook.getPrice();
                mostExpensive = currBook;
            }
        }

        return mostExpensive;
    }

    public Book getMostRecent() {
        Book mostRecent = null;
        int mostRecentYear = 0;

        for (Book currBook: books) {
            if (currBook.getYear() > mostRecentYear) {
                mostRecent = currBook;
                mostRecentYear = currBook.getYear();
            }
        }

        return mostRecent;
    }

    public Book[] snapshotArray() {
        Book[] bookArray = new Book[this.size()];

        bookArray = this.books.toArray(bookArray);
        return bookArray;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> newBookList = new ArrayList<>();
        for (Book currBook: books) {
            newBookList.add(currBook);
        }

        return newBookList;
    }
}