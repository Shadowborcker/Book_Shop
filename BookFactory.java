import java.util.Calendar;

public class BookFactory {

    public Book newBook() {

        String name, surname, author, title, publisher;
        int year, pages, quantity;
        double price;
        UserInputReader reader = new UserInputReader();
        System.out.println("Enter author's name");
        name = reader.askInput();
        System.out.println("Enter author's surname");
        surname = reader.askInput();
        author = (surname + " " + name);
        System.out.println("Enter title");
        title = reader.askInput();
        System.out.println("Enter publisher");
        publisher = reader.askInput();
        System.out.println("Enter the year of publishing");
        while (true) {
            System.out.println("Enter the year of publishing");
            year = Integer.parseInt(reader.askInput());
            if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("Invalid year");
            } else break;
        }
        while (true) {
            System.out.println("Enter number of pages");
            pages = Integer.parseInt(reader.askInput());
            if (pages <= 5) {
                System.out.println("Minimum number of pages is 5");
            } else break;

        }
        while (true) {
            System.out.println("Enter pricetag");
            price = Double.parseDouble(reader.askInput());
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
