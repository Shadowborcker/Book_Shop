import java.util.Calendar;

public class BookFactory {

    public Book newBook() {

        String name, surname, author, title, publisher;
        int year, pages, quantity;
        double price;
        UserInputReader reader = new UserInputReader();
        name = reader.askInput("Enter author's name");
        surname = reader.askInput("Enter author's surname");
        author = (surname + " " + name);
        title = reader.askInput("Enter title");
        publisher = reader.askInput("Enter publisher");
        while (true) {
            year = Integer.parseInt(reader.askInput("Enter the year of publishing"));
            if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("Invalid year");
            } else break;
        }
        while (true) {
            pages = Integer.parseInt(reader.askInput("Enter number of pages"));
            if (pages <= 5) {
                System.out.println("Minimum number of pages is 5");
            } else break;

        }
        while (true) {
            price = Double.parseDouble(reader.askInput("Enter pricetag"));
            if (price <= 0) {
                System.out.println("Minimum price is 1 Rub.");
            } else break;
        }
//        while (true) {
//                System.out.println("Enter quantity");
//                quantity = Integer.parseInt(reader.askInput());
//                if (quantity <= 0) {
//                    System.out.println("Minimum quantity is 1");
//                } else break;
//        }
        return new Book(author, title, publisher, year, pages, price);

    }
}
