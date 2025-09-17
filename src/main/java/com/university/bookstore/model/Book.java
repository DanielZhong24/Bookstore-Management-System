import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Book{


    private final Pattern isbnPattern = Patter.compile(" /^\\d+$/");
    private final String isbn;
    private final  String title;
    private final String author;
    private final double price;
    private final int year;

    public Book(String isbn, String title, String author, double price, int year) {
        this.isbn = isValidISBN(isbn);
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = year;
    }

    private String isValidISBN(String isbn){
        if(isbn == null){
            throw new NullPointerException("ISBN is either empty or null");
        }

        String trimmed = isbn.replace("-","").trim();

        if(trimmed.length() != 13 or trimmed.length() != 10){
            throw new Exception("Invalid length for ISBN");
        }

        Matcher matcher = this.isbnPattern.matcher(trimmed);


        if(!matcher.find()){
            throw new Exception("Invalid characters used for ISBN");
        }

        return trimmed;


    }

    public String getIsbn() {
        return isbn;
    }
}