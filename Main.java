
/*
Создаём книжный магазин.
Цель - приложение с консольным интерфейсом, хранящее книги в виде записей в файле на жестком диске.
Функционал условынй - добавление, удаление, обмен и покупка книг.
Работа со списком книг - сортировка, поиск.
*/

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        File shop_book_depository = new File("Shop");
        File shop_books = new File(shop_book_depository, "Books");

        //создаём папку магазина и файл книгохранилища
        if (!shop_book_depository.exists()) {
            shop_book_depository.mkdir();
        }
        if (!shop_books.exists()) {
            shop_books.createNewFile();
        }
        // Заполняем библиотеку магазина, если она пуста
        ArrayList<Book> library = FillLibrary();
        if (shop_books.length() == 0) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shop_books))) {
                for (Book b : library) {
                    oos.writeObject(b);
                }

            }
        }

        System.out.println("Welcome to Black Books, how may I be of service?");
        System.out.println("Type \"commands\" for commands list");
        System.out.println();

        // блок команд
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input command");
            String command = reader.readLine();

            switch (command.toLowerCase()) {
                case "commands":
                    System.out.println("Show books — shows the list of all books in store");
                    System.out.println("Refill — refills shop's repository with new books");
                    System.out.println("Add book — adds new book to shop's depository");
                    System.out.println("Remove book — removes selected book from shop's depository");
                    System.out.println("Sort books — sorts all books by user defined criteria");
                    System.out.println("Find book — searches for a certain book in shop's depository");
                    System.out.println("Exit — leave Black Books");
                    System.out.println();
                    break;

                case "refill" :
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shop_books))) {
                        for (Book b : library) {
                            oos.writeObject(b);
                        }
                    }
                    System.out.println("Shop's depository refilled");
                    break;

                case "show books":
                    System.out.println("Books currently in store: ");

                    try {
                        ArrayList<Book> temp = ReadFile(shop_books);
                        for (Book b : temp) {
                            System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                    + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
                                    + b.price + " Rub." + "/ Quantity: " + b.quantity);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                    break;
                case "add book": {
                    ArrayList<Book> temp = ReadFile(shop_books);
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shop_books))) {
                        AddBook(temp);
                        for (Book b : temp) {
                            oos.writeObject(b);
                        }

                    } catch (EOFException e) {
                        break;
                    }

                    break;
                }
                case "remove book": {
                    ArrayList<Book> temp = ReadFile(shop_books);
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shop_books))) {
                        RemoveBook(temp);
                        for (Book b : temp) {
                            oos.writeObject(b);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                    break;
                }
                case "sort books": {
                    System.out.println("Enter sorting criteria");
                    System.out.println("Author/Title/Publisher/Year/Number of pages/Price/Quantity");
                    String srt = reader.readLine();
                    try {
                        ArrayList<Book> temp_sort = ReadFile(shop_books);
                        SortBooks(temp_sort, srt);
                        for (Book b : temp_sort) {
                            System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                    + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
                                    + b.price + " Rub." + "/ Quantity: " + b.quantity);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                    break;
                }
                case "find book": {
                    ArrayList<Book> temp = ReadFile(shop_books);
                    FindBook(temp);
                    break;
                }


            }
            if (command.toLowerCase().equals("exit")) {
                reader.close();
                System.out.println("See you again sir, have a nice day!");
                break;
            }


        }

    }

    private static class Book implements Serializable {
        String author;
        String title;
        String publisher;
        int year;
        int pages;
        int price;
        int quantity;

        private Book(String author, String title, String publisher, int year, int pages, int price, int quantity) {
            this.author = author;
            this.title = title;
            this.publisher = publisher;
            this.year = year;
            this.pages = pages;
            this.price = price;
            this.quantity = quantity;
        }


    }

    // Создание базовой библиотеки из 10 публикаций.
    private static ArrayList<Book> FillLibrary() {

        Book book1 = new Book("King Stephen", "Shining", "Doubleday",
                1977, 447, 549, 30);
        Book book2 = new Book("King Stephen", "Cujo", "Viking Press",
                1981, 319, 453, 20);
        Book book3 = new Book("Schildt Herbert", "Java: A Beginner's Guide", "Oracle",
                2002, 602, 1800, 15);
        Book book4 = new Book("Schildt Herbert", "Java: The Complete Reference", "Oracle",
                1996, 712, 3600, 17);
        Book book5 = new Book("Tolkien John Ronald Reuel", "The Hobbit or There and Back Again",
                "HM", 1937, 313, 352, 23);
        Book book6 = new Book("Tolkien John Ronald Reuel", "Leaf by Niggle", "Newbook",
                1945, 252, 300, 31);
        Book book7 = new Book("Tolkien John Ronald Reuel", "The Fellowship of the Ring",
                "George Allen & Unwin", 1954, 423, 425, 22);
        Book book8 = new Book("Tolkien John Ronald Reuel", "The Two Towers",
                "George Allen & Unwin", 1954, 352, 425, 20);
        Book book9 = new Book("Tolkien John Ronald Reuel", "Return of the King",
                "George Allen & Unwin", 1955, 416, 425, 27);
        Book book10 = new Book("Harrison Harry", "Deathworld", "Harry&Co",
                1960, 320, 270, 12);


        return new ArrayList<>((Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10)));

    }

    // Метод чтения книг из файла в список.
    private static ArrayList<Book> ReadFile(File f) throws IOException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ArrayList<Book> list = new ArrayList<>();
            while (true) {
                try {
                    list.add((Book) ois.readObject());
                } catch (EOFException | ClassNotFoundException e) {
                    return list;
                }
            }
        }
    }

    // Метод ввода данных книги
    private static Book Newbook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name, surname, author, title, publisher;
        int year, pages, price, quantity;
        while (true) {

            try {
                System.out.println("Enter author's name");
                name = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid author's name");
            }
        }

        while (true) {
            try {
                System.out.println("Enter author's surname");
                surname = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid author's surname");
            }
        }

        author = (surname + " " + name);
        while (true) {
            try {
                System.out.println("Enter title");
                title = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid book title");
            }
        }
        while (true) {
            try {
                System.out.println("Enter publisher");
                publisher = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid publisher");
            }
        }
        while (true) {
            try {
                System.out.println("Enter the year of publishing");
                year = Integer.parseInt(reader.readLine());
                if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                    System.out.println("Invalid year");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid year");
            }
        }
        while (true) {
            try {
                System.out.println("Enter number of pages");
                pages = Integer.parseInt(reader.readLine());
                if (pages <= 5) {
                    System.out.println("Minimum number of pages is 5");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid number of pages");
            }
        }

        while (true) {
            try {
                System.out.println("Enter pricetag");
                price = Integer.parseInt(reader.readLine());
                if (price <= 0) {
                    System.out.println("Minimum price is 1 Rub.");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid pricetag");
            }
        }
        while (true) {
            try {
                System.out.println("Enter quantity");
                quantity = Integer.parseInt(reader.readLine());
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
        return new Book(author, title, publisher, year, pages, price, quantity);

    }

    // Метод для добавления книг в библиотеку вручную.
    private static void AddBook(ArrayList<Book> list) {

        Book b1 = Newbook();
        boolean wasFound = false;
        for (Book b : list) {
            if (b.author.toLowerCase().equals(b1.author.toLowerCase())
                    && b.title.toLowerCase().equals(b1.title.toLowerCase())
                    && b.publisher.toLowerCase().equals(b1.publisher.toLowerCase())
                    && (b.year == b1.year) && (b.pages == b1.pages)
                    && (b.price == b1.price)) {
                b.quantity += b1.quantity;
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
    private static void RemoveBook(ArrayList<Book> list) {
        Book b1 = Newbook();
        Iterator<Book> bookIterator = list.iterator();

        boolean wasFound = false;
        while (bookIterator.hasNext()) {
            Book b = bookIterator.next();
            if (b.author.toLowerCase().equals(b1.author.toLowerCase())
                    && b.title.toLowerCase().equals(b1.title.toLowerCase())
                    && b.publisher.toLowerCase().equals(b1.publisher.toLowerCase())
                    && (b.year == b1.year) && (b.pages == b1.pages)
                    && (b.price == b1.price) && b.quantity > 1) {
                wasFound = true;
                b.quantity -= b1.quantity;
            }

            if (b.quantity <= 0) {
                bookIterator.remove();
            }

        }
        if (wasFound) {
            System.out.println("Book(s) removed successfully");
            System.out.println();
        }
        if (!wasFound) {
            System.out.println("No such book in store, sir");
            System.out.println();
        }


    }

    // Метод сортировки списка книг по разным критериям.
    private static void SortBooks(ArrayList<Book> list, String s) {

        switch (s.toLowerCase()) {

            case "author":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.author.compareTo(o2.author);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "title":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.title.compareTo(o2.title);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "publisher":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.publisher.compareTo(o2.publisher);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "year":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.year - (o2.year);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "number of pages":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.pages - (o2.pages);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "price":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.price - (o2.price);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;
            case "quantity":
                Collections.sort(list, new Comparator<Book>() {
                            @Override
                            public int compare(Book o1, Book o2) {
                                return o1.quantity - (o2.quantity);
                            }
                        }
                );
                System.out.println("Books sorted by " + s);
                break;

            default:
                System.out.println("Unknown criteria");
        }
    }

    private static void FindBook(ArrayList<Book> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Book> found = new ArrayList<>();
        String name, surname, author, title;
        while (true) {
            try {
                System.out.println("Enter author's name");
                name = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid author's name");
            }
        }
        while (true) {
            try {
                System.out.println("Enter author's surname");
                surname = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid author's surname");
            }
        }
        while (true) {
            try {
                System.out.println("Enter title");
                title = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid book title");
            }
        }
                author = (surname + " " + name);


        boolean wasFound = false;
        for (Book b : list) {
            if (b.author.toLowerCase().equals(author.toLowerCase())
                    && b.title.toLowerCase().equals(title.toLowerCase())) {
                found.add(b);
                wasFound = true;
            }
        }
        if (wasFound) {
            System.out.println("Here is what we managed to find, sir: ");
            for (Book b : found) {
                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
                System.out.println();
            }
        }
        if (!wasFound) {
            System.out.println("No such book in store, sir");
            System.out.println();
        }

    }

}

