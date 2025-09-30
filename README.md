
# Bookstore management system
## Youtube video
[![Lab 1: Jinyang, Andrew, Shrey, Courage](https://img.youtube.com/vi/rgJRQYZ_0iM/0.jpg)](https://www.youtube.com/watch?v=rgJRQYZ_0iM)

## Reflection

1. Array vs ArrayList: Provide three specific scenarios where a fixed-size Book[] would be preferable to an ArrayList<Book>. Consider memory usage, performance, and API design.

A fixed-size Book[] would be preferable to an ArrayList when we know the fixed size in advance before creating the array, and knowing that the size will not change. Meaning that dynamic resizing form an ArrayList is not needed
Array Book[] would be better when you need the performance for having direct access to an element inside the array by indexing, where the elements are located next to each other in memory.
Array Book[] is preferable over an ArrayList especially when it comes to returning a copy that does not affect the original item as a fixed size, whereas when returned as an ArrayList, you still have access to adding or removing objects when you are not supposed to.

2. Amortized Analysis: Explain the amortized O(1) cost of ArrayList.add(). Include a numerical example showing the doubling strategy for an ArrayList growing from size 1 to 1000.

Every ArrayList has an initial capacity, and when it reaches that capacity, the ArrayList size will double, and then copy all the original elements over to the new ArrayList with the doubled size, and this will be an O(N) operation because of the frequent copying. The reason the add method is amortized as an O(1) operation is that the O(N) operation for doubling and copying does not happen all the time, only when it is strictly necessary, and that the O(1) operation for adding to the ArrayList happens way more frequently than the copying over operation, so the average time complexity for ArrayList.add() is O(1).

For example, with an ArrayList of starting size 1, the first add does not need a resize. But the second add will require the ArrayList size to be doubled to 2, copy over the old element, and then add the new one. For the third add, the ArrayList size will need to be doubled to 4, copy over the old elements, and then add the new element. And essentially, the ArrayList size will keep growing whether or not the size is full, and doubling again at sizes 8, 16, 32, 64 etc. But within these size doubles, they allow for a lot more O(1) add operations before having to double again.

When going up to size 1000, the ArrayList will essentially have to copy over 1023 elements due to the size doubling (1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512), and the add method only happens once; 1000 times total. So the total number of operations from size 1 to 1000 is 2023, and for adding 1000 times, that is 2023/1000 which is close to a constant of 2 operations for one addition. Therefore, the average time complexity for ArrayList.add() is an amortized O(1)

3. Alternative Structures: Your bookstore frequently queries by ISBN. Compare the performance of:
• Current ArrayList: O(n) lookup
• HashMap¡String, Book¿: expected O(1) lookup
• TreeMap¡String, Book¿: O(log n) lookup
When would you choose each? Consider memory overhead and operation mix

ArrayList is good for smaller size bookstores, looking up by ISBN will require you to iterate over every single book for its ISBN which can be quite slow. ArrayList is also O(N) space complexity for N is the number of elements stored in the ArrayList. Essentially, good for adding, but slow for looking up

Hashmap is better for much larger size bookstores, and excellent when it comes to looking up ISBN’s as a key. Duplicate keys are also not allowed in a hashmap. Compared with an ArrayList, you would still have to check if the ISBN (key) already exists by looking up the key value, compared to ArrayList where you would have to iterate over to find the ISBN

TreeMap is similar to a HashMap, except a TreeMap has the order of the keys sorted, whereas a HashMap will not. If the order of the ISBN keys matter, then a TreeMap would be better as when you are iterating over it, you can expect it to be in order, whereas a HashMap, you won’t exactly know the order

4. Immutability Benefits: List five specific benefits of making Book immutable. How would your API change if Book was mutable? What bugs might arise

    1. Thread safety. Immutable objects can be safely shared across multiple threads without synchronization.

    2. Safe hashing and collections, since the Book class is immutable, its hashcode and equals methods remain consistent. Meaning no subclass can be created nor override the methods. 
    It also makes it safe to use as a key in Hashmaps.
   
    3. Immutable fields. Once the book is created, its properties like ISBN, author, titles are unchangeable.
   
    4. Easier Testing & Debugging. Immutable objects are simpler to test because their state cannot change unexpectedly.
   
    5. Safe Sharing & Caching. Two parts of your application can reference the same Book without one accidentally modifying it for the other.
    
    If Book were mutable, the API would have to change quite a bit. First is the need of adding setter methods. Then, you would need to validate the data while using the setters as well. The equals and hashcode methods would become unreliable, because the field can be modified later, making the hash unusable.

    Some bugs that could arise from that could be: equality checks not working, caching issues, issues with other parts of the coding referencing an object that has been modified.

5.  Defensive Copying: Explain why returning new ArrayList<>(internalList) is crucial. Provide a code example showing what could go wrong without it.
    
    Defensive copying is important because if a class exposes its internal storage directly through a getter, external code can modify the data inside the object.

    The data will not be encapsulated and can lead to bugs such as change the price to 0. 

    By returning a defensive copy we can give them a deep copied version of the data, they can modify the list as much as they want but the internal list will not be affected.

    For example, this is what can happen if we don't have defensive copy.
    ```java
    import java.util.ArrayList;
    import java.util.List;
    
    class BookArrayUtils {
        private List<String> books = new ArrayList<>();
    
        public void addBook(String book) {
            books.add(book);
        }
    
        // Unsafe: returns the internal list directly
        public List<String> getBooks() {
            return books;
        }
    }
    
    public class Main {
         static void main(String[] args) {
            BookArrayUtils library = new BookArrayUtils();
            library.addBook("Java Basics");
            library.addBook("Python Essentials");
    
            List<String> external = library.getBooks();
            external.clear(); // Oops! Internal list is now empty
    
            System.out.println(library.getBooks().size()); // Output: 0
        }
    }
    ```
    The problem here is that the user can freely modify the internal arraylist, which is not safe for the data stored.

6.  Interface Segregation: Should BookstoreAPI be split into multiple interfaces (e.g., SearchableBookstore, MutableBookstore)? Discuss pros and cons

    Yes, splitting into multiple interfaces could be beneficial. Some of the pros include making the application more flexible. For example, if a client requires a simple dashboard interface, they might have no use for add or removeByISBN functions. Having separate interfaces allows clients to depend only on the methods they need, avoiding unnecessary dependencies.

    Some cons include increased complexity, which can make it harder for other developers to understand the APIs at first glance. Additionally, some classes might need to implement multiple interfaces, which can lead to duplicated method signatures.


7.3 One Million Books  

When expanding the bookstore to millions of entries, raw linear searches or simple arrays will no longer be sufficient. Indexing becomes the primary driver of query performance. The ISBN, as a globally unique identifier, should serve as the main index to guarantee constant time lookups for exact matches. Beyond that, indexes should be tailored to the common access patterns. For instance, frequent searches by author name or book title can be sped up by normalizing these fields (e.g., converting to lowercase, trimming punctuation) and creating secondary indexes. For queries that rely on ranges, such as filtering by publication year or price brackets, ordered indexes such as B-trees or skip lists provide efficient navigation across large spans of data.  

Caching also plays an important role in ensuring low latency. Instead of recalculating results every time, hot items like frequently requested ISBNs, trending authors, or recently released books can be stored in memory. Aggregated values such as total value of inventory or most recent additions can also be cached and updated incrementally, rather than recomputed from scratch. Result caching for popular search queries is useful as well, provided invalidation strategies are in place so that updates remain consistent.  

For persistence, the choice between relational and non-relational databases depends on requirements. A relational system such as PostgreSQL or MySQL is often the first choice when strong consistency, joins across multiple entities (publishers, orders, customers), and transactional safety are needed. Well-defined B-tree indexes on ISBN, normalized author and title fields, and publication year ensure efficient performance. If the catalog grows so large that horizontal scaling is unavoidable, a NoSQL database may be more appropriate. Document stores like MongoDB can handle flexible schemas, while key-value systems allow extremely fast ISBN lookups. For complex text-based queries, pairing the main store with a search engine such as Elasticsearch is often the most effective hybrid solution.  

Pagination ensures that users can browse results without overwhelming the system. Offset-based pagination for example LIMIT 20 OFFSET 1000 is simple but becomes inefficient on very deep pages. Keyset pagination avoids this issue by using the last retrieved row as a cursor, ensuring consistent ordering and better performance even when new records are inserted. Exposing a nextPageToken or similar cursor makes it easy for clients to continue fetching data from where they left off.  

Together, indexing, caching, database integration, and pagination form the backbone of scaling this system to millions of books while keeping performance predictable and responsive.  

• Indexing
  - Primary: ISBN (unique lookups).  
  - Secondary: normalized title, normalized author, publication year.  
  - Range queries: use ordered structures for year and price filters.  

• Caching 
  - Popular ISBN lookups stored in-memory.  
  - Frequently used queries (e.g., common authors, genres).  
  - Aggregates like total value and most recent entries updated incrementally.  

• Database integration

  - SQL (PostgreSQL/MySQL) for ACID guarantees, joins, and transactional integrity.  
  - NoSQL (MongoDB, DynamoDB) or a hybrid model for very large-scale horizontal distribution.  
  - External search engine (Elasticsearch) for fuzzy or full-text title/author queries.  

• Pagination 
  - Use keyset (seek) pagination for efficient large-scale navigation.  
  - Provide stable ordering (e.g., title + ISBN) and return a next-page token.  


    

8. Concurrency: Multiple threads need to access your bookstore. How would you make it threadsafe?
Compare:
• synchronized methods
• ReadWriteLock
• ConcurrentHashMap
• Immutable snapshot approach

 Synchronized methods are pretty easy to use as methods just need the synchronized keyword added. They ensure that only one thread can execute a single method at a time on an object.They ensure memory visibility along with mutual exclusion. However, synchronized methods can cause deadlocks if there are many objects that have synchronized methods that are locked by threads in different orders. They can also increase the time taken to get a response from the program since threads get blocked while waiting for a synchronized method.

Readwritelock separates read and write operations from each other. It allows threads to read simultaneously which reduces contention. The downside of this is that it’s more complex and isn’t good to use in everything.


ConcurrentHashMap is a threadsafe implementation that can be safely accessed by multiple threads. It has high performance under concurrency since it uses fine grained locking and is scalable since it’s designed for concurrent environments with many threads. However, it is pretty complex making it more difficult to debug it for any performance issues it may contain and it’s restricting since null can’t be stored inside it.

An immutable snapshot approach is a design strategy where readers can work in the snapshot without synchronization since it’s immutable and writers can build and replace an old snapshot with a new one. Since it can be replaced or updated, it can demand a lot of memory if the snapshot that’s being updated is large and it’s not great for write-heavy applications since updating it requires it to be rebuilt.

9. Design a simple performance monitoring system. What metrics would you track? How would you identify bottlenecks?

System level metrics to keep track of cpu, memory, network and disk usage on user’s devices, application level metrics to see how the application operates, how quickly it runs and the amount of times it runs into errors. In order to identify bottlenecks I would need to check the entire process for the application, check how long it takes for it to run, look into what has the longest run time out of everything in the application.

10. Is 100% code coverage always desirable? Provide examples of code that might not need testing and code that needs extensive testing despite being simple.

No, 100% code coverage isn’t always desirable. 100% code coverage doesn’t mean that the logic in the code is verified. Trying to achieve 100% code coverage is a waste of time and can potentially make the code more complex. Some code that might not require testing would be getter and setter methods. While some code that does need extensive testing despite being simple would be methods that run operations and return the result of said operation.


11.Test Pyramid for the Bookstore System


Unit Tests (≈70%)


Book constructor rejects invalid inputs: such as negative price, null title, or impossible year.

Book equality and hashing: confirm that two books with the same ISBN are treated as equal in sets/maps.

BookstoreArrayList prevents duplicates: adding a book with an existing ISBN doesn’t increase size.

Title and author lookups: searches ignore case and return exact matches only.

BookArrayUtils filtering/sorting: filterByYearRange excludes out-of-range items, and sortByPrice places nulls at the end.



Integration Tests (≈20%)


BookstoreArrayList with BookArrayUtils: add several books to the store, then apply filterByDecade on snapshotArray() to confirm the two components work together.

Chained operations: after inserting books, run removeDuplicates followed by countByDecade to ensure unique counts are produced correctly.

Search + inventory consistency: add and remove books, then verify that findByYear and inventoryValue both reflect the updated store.


End-to-End Tests (≈10%)


User workflow simulation: a user adds books, searches by author, deletes one, and then checks that the most recent and most expensive books returned are correct.

Bulk data import scenario: import an array containing duplicates and null entries, clean it using removeDuplicates, load it into the store, and confirm the snapshot only shows valid unique items.
