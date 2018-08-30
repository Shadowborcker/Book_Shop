import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class BookService {

    // Метод для добавления книг в библиотеку вручную.
    public void addBook(ArrayList<Book> list) {
        int quantity;
        UserInputReader reader = new UserInputReader();
        BookFactory book = new BookFactory();
        Book b1 = book.newBook();
        while (true) {
            try {
                quantity = Integer.parseInt(reader.askInput("How many books would you like to add?"));
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
        b1.setQuantity(quantity);
        boolean wasFound = false;
        for (Book b : list) {
            if (b.equals(b1)) {
                b.setQuantity(b.getQuantity() + b1.getQuantity());
                wasFound = true;
            }
        }
        if (!wasFound) {
            list.add(b1);
        }
    }

    // Метод для удаления книг из библиотеки вручную.
    public void removeBook(ArrayList<Book> list) {
        int quantity;
        UserInputReader reader = new UserInputReader();
        BookFactory book = new BookFactory();
        Book b1 = book.newBook();
        while (true) {
            try {
                quantity = Integer.parseInt(reader.askInput("How many books would you like to remove?"));
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
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
        } else {
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
                list.sort(Comparator.comparing(Book::getAuthor));
                break;
        }

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

    // Создание базовой библиотеки из 10 публикаций.
    public ArrayList<Book> fillStore() {
        Book b1 = new Book("King Stephen", "Shining", "Doubleday",
                1977, 447, 549);
        Book b2 = new Book("King Stephen", "Cujo", "Viking Press",
                1981, 319, 453);
        Book b3 = new Book("Schildt Herbert", "Java: A Beginner's Guide", "Oracle",
                2002, 602, 1800);
        Book b4 = new Book("Schildt Herbert", "Java: The Complete Reference", "Oracle",
                1996, 712, 3600);
        Book b5 = new Book("Tolkien John Ronald Reuel", "The Hobbit or There and Back Again",
                "HM", 1937, 313, 352);
        Book b6 = new Book("Tolkien John Ronald Reuel", "Leaf by Niggle", "Newbook",
                1945, 252, 300);
        Book b7 = new Book("Tolkien John Ronald Reuel", "The Fellowship of the Ring",
                "George Allen & Unwin", 1954, 423, 425);
        Book b8 = new Book("Tolkien John Ronald Reuel", "The Two Towers",
                "George Allen & Unwin", 1954, 352, 425);
        Book b9 = new Book("Tolkien John Ronald Reuel", "Return of the King",
                "George Allen & Unwin", 1955, 416, 425);
        Book b10 = new Book("Harrison Harry", "Deathworld", "Harry&Co",
                1960, 320, 270);
        ArrayList<Book> list = new ArrayList<>((Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)));
        for (Book b : list) {
            b.setQuantity(50);
        }
        return list;

    }

    // Заполнение домашней библиотеки.
    public ArrayList<Book> fillLibrary() {

        Book b1 = new Book("King Stephen", "Shining", "Doubleday",
                1977, 447, 549);
        Book b2 = new Book("King Stephen", "Cujo", "Viking Press",
                1981, 319, 453);
        Book b3 = new Book("Schildt Herbert", "Java: A Beginner's Guide", "Oracle",
                2002, 602, 1800);
        Book b4 = new Book("Schildt Herbert", "Java: The Complete Reference", "Oracle",
                1996, 712, 3600);
        Book b5 = new Book("Tolkien John Ronald Reuel", "The Hobbit or There and Back Again",
                "HM", 1937, 313, 352);
        Book b6 = new Book("Tolkien John Ronald Reuel", "Leaf by Niggle", "Newbook",
                1945, 252, 300);
        Book b7 = new Book("Tolkien John Ronald Reuel", "The Fellowship of the Ring",
                "George Allen & Unwin", 1954, 423, 425);
        Book b8 = new Book("Tolkien John Ronald Reuel", "The Two Towers",
                "George Allen & Unwin", 1954, 352, 425);
        Book b9 = new Book("Tolkien John Ronald Reuel", "Return of the King",
                "George Allen & Unwin", 1955, 416, 425);
        Book b10 = new Book("Harrison Harry", "Deathworld", "Harry&Co",
                1960, 320, 270);
        ArrayList<Book> list = new ArrayList<>((Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)));
        for (Book b : list) {
            b.setQuantity(1);
        }
        return list;

    }
}
