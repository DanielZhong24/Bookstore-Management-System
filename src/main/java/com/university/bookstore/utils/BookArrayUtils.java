import java.util.Arrays;


public final class BookArrayUtils{


    private BookArrayUtils() {
        // Utility class - prevent instantiation

    }


        public static int countBeforeYear (Book[]books,int yearCutoff){



        int numBooks=0;

        if(books == null){
            return 0;
        }

        for(Book date : books){

            if(date.getYear() < yearCutoff){

                numBooks++;
            }
            return numBooks;


        }






        }


        public static int countByAuthor (Book[]books, String author){


        int numBooks = 0;

        if(books == null || author== null){
            return null;

        }

        for(Book a : books){
            if(  Book != null && a.getAuthor().equals author)
                numBooks++;
        }


        return numBooks;

        }


        // Filtering operations (return compact arrays)

        public static Book[] filterPriceAtMost (Book[]books,double maxPrice){

        int bookCount=0;
        int i =0;



        if(maxPrice < 0){

            throw new IllegalArgimenntException("You can't have a max price lower then 0");
        }


        if(books == null){
            return null;
        }

        for(Book price : books){
            if(books != null && price.getPrice() <= maxPrice) {
                bookCount++;
            }
        }


        Book [] list = new Book[bookCount];



            for(Book price : books){

                if(books != null && price.getPrice() <= maxPrice) {


                    list[i] = book;

                    i++;




                }
            }


            return list;






        }


        public static Book[] filterByDecade (Book[]books,int decade){

        int bookCount=0;

        if (books == null){

            return null;
        }


        for(Book s : books){

            if( books != null && )
        }






        }

        // Sorting operations (modify in-place)


        public static void sortByPrice (Book[]books);

        public static void sortByYear (Book[]books);

        // Statistics


        public static double averagePrice(Book[] books);
        public static Book findOldest(Book[] books);

         // Array manipulation

    public static Book[] merge(Book[] arr1, Book[] arr2);
    public static Book[] removeDuplicates(Book[] books);






}