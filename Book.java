import java.io.Serializable;

public class Book implements Serializable {

        String author;
        String title;
        String publisher;
        int year;
        int pages;
        double price;
        int quantity;

        public Book(String author, String title, String publisher, int year, int pages, double price, int quantity) {
            this.author = author;
            this.title = title;
            this.publisher = publisher;
            this.year = year;
            this.pages = pages;
            this.price = price;
            this.quantity = quantity;
        }



}
