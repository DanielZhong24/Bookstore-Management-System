public interface BookstoreAPI {
    boolean add(Book book);
    boolean removeByIsbn(String isbn);
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String titleQuery);
    List<Book> findByAuthor(String authorQuery);
    List<Book> findByPriceRange(double minPrice, double maxPrice);
    List<Book> findByYear(int year);
    // Analytics operations
    int size();
    double inventoryValue();
    Book getMostExpensive();
    Book getMostRecent();

    // Export operations
    Book[] snapshotArray();
    List<Book> getAllBooks();
    public boolean add(Book book){

    }
    public Book findByIsbn(String isbn){

    }
    public boolean findByIsbn(String titleQuery){

    }
    public List<Book> findByAuthor(String autthorQuery){

    }
    public List<Book> findByPriceRange (double minPrice, double maxPrice){

    }
    public List<Book> findByYear(int year){

    }
    public int size(){

    }
    public double inventoryValue(){

    }
    public Book getMostExpensiveBook(){

    }
    public Book getMostRecent(){
        
    }
}
