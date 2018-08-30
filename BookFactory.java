import java.io.IOException;

public class BookFactory {

    public Book newBook() throws IOException {

        String name, surname, author, title, publisher;
        int year, pages, quantity;
        double price;
        UserInputReader reader = new UserInputReader();
        name = reader.askString("Enter author's name");
        surname = reader.askString("Enter author's surname");
        author = (surname + " " + name);
        title = reader.askString("Enter title");
        publisher = reader.askString("Enter publisher");
        year = reader.askYear("Enter the year of publishing");
        pages = reader.askInt("Enter number of pages");
        price = reader.askDouble("Enter pricetag");
        return new Book(author, title, publisher, year, pages, price);

    }
}
