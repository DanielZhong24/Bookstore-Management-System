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
