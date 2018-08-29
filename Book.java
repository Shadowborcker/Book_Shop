import java.io.Serializable;
import java.util.Objects;
import java.text.DecimalFormat;

public class Book implements Serializable {

    private String author;
    private String title;
    private String publisher;
    private int year;
    private int pages;
    private double price;
    private int quantity;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Переопределяем equals и hashCode для сравнения книг, поле количество не учитывается.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                pages == book.pages &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, publisher, year, pages, price);
    }

    // Переопределяем toString для вывода на экран.
    private DecimalFormat numberFormat = new DecimalFormat("#.00");
    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", price=" + numberFormat.format(price) +
                ", quantity=" + quantity +
                '}';
    }

    // Полный конструктор.
    public Book(String author, String title, String publisher, int year, int pages, double price) {
        setAuthor(author);
        setTitle(title);
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }


}
