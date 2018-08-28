import java.io.IOException;
import java.util.Calendar;

public class BookFactory {

    public Book newBook() {
        String name, surname, author, title, publisher;
        int year, pages, quantity;
        double price;
        System.out.println("Enter author's name");
        name = UserInputReader.askString();
        System.out.println("Enter author's surname");
        surname = UserInputReader.askString();
        author = (surname + " " + name);
        System.out.println("Enter title");
        title = UserInputReader.askString();
        System.out.println("Enter publisher");
        publisher = UserInputReader.askString();
        System.out.println("Enter the year of publishing");
        while (true) {
            System.out.println("Enter the year of publishing");
            year = (int)UserInputReader.askNumber();
            if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("Invalid year");
            } else break;
        }
        while (true) {
            System.out.println("Enter number of pages");
            pages = (int)UserInputReader.askNumber();
            if (pages <= 5) {
                System.out.println("Minimum number of pages is 5");
            } else break;

        }
        while (true) {
            System.out.println("Enter pricetag");
            price = UserInputReader.askNumber();
            if (price <= 0) {
                System.out.println("Minimum price is 1 Rub.");
            } else break;
        }
        while (true) {
            try {
                System.out.println("Enter quantity");
                quantity = UserInputReader.askNumber();
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
        return new Book(author, title, publisher, year, pages, price, quantity);

    }
}
