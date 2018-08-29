import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BookService {

    // Метод для добавления книг в библиотеку вручную.
    public void addBook(ArrayList<Book> list) {
        BookFactory book = new BookFactory();
        Book b1 = book.newBook();
        boolean wasFound = false;
        for (Book b : list) {
            if (b.equals(b1)){
                b.setQuantity(b.getQuantity()+b1.getQuantity());
                wasFound = true;
            }
        }
        if (!wasFound) {
            list.add(b1);
        }
        System.out.println("New book added");
        System.out.println();

    }
    // Метод для удаления книг из библиотеки вручную.
    public void removeBook(ArrayList<Book> list) {
        int quantity;
        UserInputReader reader = new UserInputReader();
        while (true) {
            try {
                System.out.println("How many books would you like to remove?");
                quantity = Integer.parseInt(reader.askInput());
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
        BookFactory book = new BookFactory();
        Book b1 = book.newBook();
        b1.setQuantity(quantity);
        Iterator<Book> bookIterator = list.iterator();
        boolean wasFound = false;
        while (bookIterator.hasNext()) {
            Book b = bookIterator.next();
            if (b.equals(b1)
                    & b.getQuantity() >= 1) {
                wasFound = true;
                b.setQuantity(b.getQuantity() - b1.getQuantity());
            }

            if (b.getQuantity() <= 0) {
                bookIterator.remove();
            }

        }
        if (wasFound) {
            System.out.println("Book(s) removed successfully");
            System.out.println();
        }
        else  {
            System.out.println("No such book, sir");
            System.out.println();
        }

    }
    // Метод сортировки списка книг по разным критериям.
    public void sortBooks(ArrayList<Book> list, String s) {
        switch (s.toLowerCase()) {
            case "author":
                list.sort(Comparator.comparing(Book::getAuthor));
                break;
            case "title":
                list.sort(Comparator.comparing(Book::getTitle));
                break;
            case "publisher":
                list.sort(Comparator.comparing(Book::getPublisher));
                break;
            case "year":
                list.sort(Comparator.comparing(Book::getYear));
                break;
            case "number of pages":
                list.sort(Comparator.comparing(Book::getPages));
                break;
            case "price":
                list.sort(Comparator.comparingDouble(Book::getPrice));
                break;
            case "quantity":
                list.sort(Comparator.comparing(Book::getQuantity));
                break;
            default:
                System.out.println("Unknown criteria");
        }
          System.out.println("Books sorted by " + s);
    }
    public ArrayList<Book> findBook(ArrayList<Book> list) {
        ArrayList<Book> found = new ArrayList<>();
        BookFactory book = new BookFactory();
        Book b1 = book.newBook();
        boolean wasFound = false;
        for (Book b : list) {
            if (b1.equals(b)) {
                wasFound = true;
                found.add(b);
            }
        }

        if (!wasFound) {
            System.out.println("No such book in store, sir");
            System.out.println();
        }
        return found;

    }
}
