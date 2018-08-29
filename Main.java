
/*
Создаём книжный магазин.
Цель - приложение с консольным интерфейсом, хранящее книги в виде записей в файле на жестком диске.
Функционал условынй - добавление, удаление, обмен и покупка книг.
Работа со списком книг - сортировка, поиск.
*/

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {


    public void main(String[] args) throws IOException {
        Storage stor = new Storage();
        stor.createFolders();
        stor.createFiles();

        // Заполняем книгохранилище магазина если оно пустое
        ArrayList<Book> storeList = fillStore();
        if (storeBooks.length() == 0) {
            try (FileOutputStream fos = new FileOutputStream(storeBooks);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Book b : storeList) {
                    oos.writeObject(b);
                }

            }
        }
//        // Заполняем корзину для тестирования
//        ArrayList<Book> bask = FillLibrary();
//        if (basket.length() == 0) {
//            try (FileOutputStream fos = new FileOutputStream(basket);
//                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                for (Book b : bask) {
//                    oos.writeObject(b);
//                }
//
//            }
//        }

        // Заполняем домашнюю библиотеку если она пуста
        ArrayList<Book> homeList = fillLibrary();
        if (homeLibrary.length() == 0) {
            try (FileOutputStream fos = new FileOutputStream(homeLibrary);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Book b : homeList) {
                    oos.writeObject(b);
                }

            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Black Books, please introduce yourself");
        String name;
        int age;
        boolean sex;
        double money;
//        while (true) {
//
//            while (true) {
//                try {
//                    System.out.println("What is your name?");
//                    name = reader.readLine();
//                    break;
//                } catch (Exception e) {
//                    System.out.println("Invalid user name");
//                }
//            }
//            while (true) {
//                try {
//                    System.out.println("Your age?");
//                    age = Integer.parseInt(reader.readLine());
//                    break;
//                } catch (Exception e) {
//                    System.out.println("Invalid age");
//                }
//            }
//            while (true) {
//                try {
//                    System.out.println("Your sex?");
//                    String sexEntered = reader.readLine();
//                    if (sexEntered.toLowerCase().equals("male")) {
//                        sex = true;
//                        break;
//                    }
//                    if (sexEntered.toLowerCase().equals("female")) {
//                        sex = false;
//                        break;
//                    }
//                } catch (Exception e) {
//                    System.out.println("Invalid sex");
//                }
//            }
//            while (true) {
//                try {
//                    System.out.println("The amount of cash you would like to spend?");
//                    money = Double.parseDouble(reader.readLine());
//                    break;
//                } catch (Exception e) {
//                    System.out.println("Invalid sum");
//                }
//            }
//            break;
//        }
//        User user = new User(name, age, sex, money);
//
//        System.out.println("Welcome " + user.name);
        System.out.println("Type \"commands\" for commands list");
        System.out.println();

        // блок команд
        while (true) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input command");
            String command = reader.readLine();

            switch (command.toLowerCase()) {
                case "commands":
                    System.out.println("Show store — displays the list of all books in store");
                    System.out.println("Show basket — displays books currently in your basket");
                    System.out.println("Show library — displays the list of all books in your home library");
                    System.out.println("Refill store — refills shop's repository with new books");
                    System.out.println("Refill library — refills your library");
                    System.out.println("Add book — adds new book to specified location");
                    System.out.println("Remove book — removes selected book from specified location");
                    System.out.println("Sort books — sorts all books in depository by user defined criteria");
                    System.out.println("Find book — searches for a certain book in shop's depository");
                    System.out.println("Add to basket — adds certain book to your basket");
//                    System.out.println("Remove from basket — removes certain book from your basket");
//                    System.out.println("Clear basket — removes all books from your basket");
//                    System.out.println("Cash out — pays for all the books in your basket and" +
//                            " sends them to your home library ");
                    System.out.println("Exit — leave Black Books");
                    System.out.println();
                    break;

                case "show store":

                    try {
                        if (storeBooks.length() != 0) {
                            ArrayList<Book> temp = readFile(storeBooks);
                            System.out.println("Books currently in store: ");
                            for (Book b : temp) {
                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
                                        + b.pages + "/ Price: "
                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
                            }
                        } else System.out.println("Store depository is empty");
                    } catch (EOFException e) {
                        break;
                    }
                    break;

                case "show basket":

                    try {
                        if (basket.length() != 0) {
                            ArrayList<Book> temp = readFile(basket);
                            System.out.println("Books currently in basket: ");
                            double totalPrice = 0;
                            for (Book b : temp) {
                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
                                        + b.pages + "/ Price: "
                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
                                totalPrice += (b.price * b.quantity);
                            }
                            DecimalFormat numberFormat = new DecimalFormat("#.00");
                            System.out.println("Total price of all books in your basket is: "
                                    + numberFormat.format(totalPrice) + " Rub.");
                        } else System.out.println("Your basket is empty");
                    } catch (EOFException e) {
                        break;
                    }
                    break;

                case "show library":

                    try {

                        if (homeLibrary.length() != 0) {
                            ArrayList<Book> temp = readFile(homeLibrary);
                            System.out.println("Books currently in your library: ");
                            for (Book b : temp) {
                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
                                        + b.pages + "/ Price: "
                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
                            }
                        } else System.out.println("Your library is empty");
                    } catch (EOFException e) {
                        break;
                    }
                    break;

                case "refill store":
                    try (FileOutputStream fos = new FileOutputStream(storeBooks);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        for (Book b : storeList) {
                            oos.writeObject(b);
                        }
                    }
                    System.out.println("Shop's depository refilled");
                    break;

                case "refill library":
                    try (FileOutputStream fos = new FileOutputStream(homeLibrary);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        for (Book b : homeList) {
                            oos.writeObject(b);
                        }
                    }
                    System.out.println("Home library refilled");
                    break;

                case "add book":
                    System.out.println("Where would you like to add a new book? (Store/Library)");
                    try {
                        String choice = reader.readLine();
                        switch (choice.toLowerCase()) {
                            case "store": {
                                ArrayList<Book> tempStore = readFile(storeBooks);
                                try (FileOutputStream fos = new FileOutputStream(storeBooks);
                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                                    addBook(tempStore);
                                    for (Book b : tempStore) {
                                        oos.writeObject(b);
                                    }

                                } catch (EOFException e) {
                                    break;
                                }
                            }
                            case "library": {
                                ArrayList<Book> tempLibrary = readFile(homeLibrary);
                                try (FileOutputStream fos = new FileOutputStream(homeLibrary);
                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                                    addBook(tempLibrary);
                                    for (Book b : tempLibrary) {
                                        oos.writeObject(b);
                                    }

                                } catch (EOFException e) {
                                    break;
                                }
                            }

                        }
                    } catch (IOException e) {
                        System.out.println("Invalid command");
                        break;
                    }

                    break;

                case "remove book":
                    System.out.println("From where would you like to remove a book? (Store/Library)");
                    try {
                        String choice = reader.readLine();
                        switch (choice.toLowerCase()) {
                            case "store": {
                                ArrayList<Book> temp = readFile(storeBooks);
                                try (FileOutputStream fos = new FileOutputStream(storeBooks);
                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                                    removeBook(temp);
                                    for (Book b : temp) {
                                        oos.writeObject(b);
                                    }
                                } catch (EOFException e) {
                                    break;
                                }
                                break;
                            }
                            case "library": {
                                ArrayList<Book> temp = readFile(homeLibrary);
                                try (FileOutputStream fos = new FileOutputStream(homeLibrary);
                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                                    removeBook(temp);
                                    for (Book b : temp) {
                                        oos.writeObject(b);
                                    }
                                } catch (EOFException e) {
                                    break;
                                }
                                break;
                            }

                        }
                    } catch (IOException e) {
                        System.out.println("Invalid command");
                        break;
                    }


                    break;


                case "sort books":
                    System.out.println("Enter sorting criteria");
                    System.out.println("Author/Title/Publisher/Year/Number of pages/Price/Quantity");
                    String srt = reader.readLine();
                    try {
                        ArrayList<Book> temp_sort = readFile(storeBooks);
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

                case "find book": {
                    ArrayList<Book> temp = new ArrayList<>();
                    temp.add(FindBook(readFile(storeBooks)));
                    if (temp.size() != 0) {
                        System.out.println("Here is what we managed to find, sir: ");
                        for (Book b : temp) {
                            System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
                                    + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
                                    + b.price + " Rub.");
                            System.out.println();
                        }
                    }
                    break;
                }


                case "add to basket": {
                    Book b1 = (FindBook(readFile(storeBooks)));
                    System.out.println("How many would you like. Sir?");
                    int numberToAdd;
                    while (true) {
                        try {
                            System.out.println("Enter quantity");
                            numberToAdd = Integer.parseInt(reader.readLine());
                            if (numberToAdd <= 0) {
                                System.out.println("Minimum quantity is 1");
                            } else break;
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Invalid quantity");
                        }
                    }
                    b1.quantity = numberToAdd;

                    try (FileOutputStream fos = new FileOutputStream(basket);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        ArrayList<Book> tempBasket = new ArrayList<>();
                        ArrayList<Book> tempStore = readFile(storeBooks);
                        if (basket.length() != 0) {
                            tempBasket = readFile(basket);
                            for (Book b : tempBasket) {
                                if (b.author.toLowerCase().equals(b1.author.toLowerCase())
                                        && b.title.toLowerCase().equals(b1.title.toLowerCase())
                                        && b.publisher.toLowerCase().equals(b1.publisher.toLowerCase())
                                        && (b.year == b1.year) && (b.pages == b1.pages)
                                        && (b.price == b1.price)) {
                                    b.quantity += b1.quantity;

                                }
                            }
                            Iterator<Book> bookIterator = tempStore.iterator();
                            while (bookIterator.hasNext()) {
                                Book b = bookIterator.next();
                                if (b.author.toLowerCase().equals(b1.author.toLowerCase())
                                        && b.title.toLowerCase().equals(b1.title.toLowerCase())
                                        && b.quantity >= 1) {
                                    b.quantity -= b1.quantity;
                                }

                                if (b.quantity <= 0) {
                                    bookIterator.remove();
                                }

                            }
                        } else {
                            tempBasket.add(b1);
                        }
                        for (Book b : tempBasket) {
                            oos.writeObject(b);
                        }

                    } catch (EOFException e) {
                        break;
                    }

                    System.out.println("Book added to your basket");
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



    private class User {
        String name;
        double money = 2500.00;

        private User(String name) {
            this.name = name;
        }


    }

    // Создание базовой библиотеки из 10 публикаций.
    private ArrayList<Book> fillStore() {

        Book book1 = new Book("King Stephen", "Shining", "Doubleday",
                1977, 447, 549);
        Book book2 = new Book("King Stephen", "Cujo", "Viking Press",
                1981, 319, 453);
        Book book3 = new Book("Schildt Herbert", "Java: A Beginner's Guide", "Oracle",
                2002, 602, 1800);
        Book book4 = new Book("Schildt Herbert", "Java: The Complete Reference", "Oracle",
                1996, 712, 3600);
        Book book5 = new Book("Tolkien John Ronald Reuel", "The Hobbit or There and Back Again",
                "HM", 1937, 313, 352);
        Book book6 = new Book("Tolkien John Ronald Reuel", "Leaf by Niggle", "Newbook",
                1945, 252, 300);
        Book book7 = new Book("Tolkien John Ronald Reuel", "The Fellowship of the Ring",
                "George Allen & Unwin", 1954, 423, 425);
        Book book8 = new Book("Tolkien John Ronald Reuel", "The Two Towers",
                "George Allen & Unwin", 1954, 352, 425);
        Book book9 = new Book("Tolkien John Ronald Reuel", "Return of the King",
                "George Allen & Unwin", 1955, 416, 425);
        Book book10 = new Book("Harrison Harry", "Deathworld", "Harry&Co",
                1960, 320, 270);


        return new ArrayList<>((Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10)));

    }

    private static ArrayList<Book> fillLibrary() {

        Book book1 = new Book("King Stephen", "Shining", "Doubleday",
                1977, 447, 549);
        Book book2 = new Book("King Stephen", "Cujo", "Viking Press",
                1981, 319, 453);
        Book book3 = new Book("Schildt Herbert", "Java: A Beginner's Guide", "Oracle",
                2002, 602, 1800);
        Book book4 = new Book("Schildt Herbert", "Java: The Complete Reference", "Oracle",
                1996, 712, 3600);
        Book book5 = new Book("Tolkien John Ronald Reuel", "The Hobbit or There and Back Again",
                "HM", 1937, 313, 352);
        Book book6 = new Book("Tolkien John Ronald Reuel", "Leaf by Niggle", "Newbook",
                1945, 252, 300);
        Book book7 = new Book("Tolkien John Ronald Reuel", "The Fellowship of the Ring",
                "George Allen & Unwin", 1954, 423, 425);
        Book book8 = new Book("Tolkien John Ronald Reuel", "The Two Towers",
                "George Allen & Unwin", 1954, 352, 425);
        Book book9 = new Book("Tolkien John Ronald Reuel", "Return of the King",
                "George Allen & Unwin", 1955, 416, 425);
        Book book10 = new Book("Harrison Harry", "Deathworld", "Harry&Co",
                1960, 320, 270);


        return new ArrayList<>((Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10)));

    }


    // Метод чтения книг из файла в список.
    private static ArrayList<Book> readFile(File f) throws IOException {

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
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


    // Метод для добавления книг в библиотеку вручную.
    private void addBook(ArrayList<Book> list) {

        Book b1 = BookFactory.newBook();
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
    private void removeBook(ArrayList<Book> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name, surname, author, title;
        int quantity;
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
        while (true) {
            try {
                System.out.println("How many books would you like to remove?");
                quantity = Integer.parseInt(reader.readLine());
                if (quantity <= 0) {
                    System.out.println("Minimum quantity is 1");
                } else break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        }
        author = (surname + " " + name);

        Iterator<Book> bookIterator = list.iterator();

        boolean wasFound = false;
        while (bookIterator.hasNext()) {
            Book b = bookIterator.next();
            if (b.author.toLowerCase().equals(author.toLowerCase())
                    && b.title.toLowerCase().equals(title.toLowerCase())
                    && b.quantity >= 1) {
                wasFound = true;
                b.quantity -= quantity;
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
            System.out.println("No such book, sir");
            System.out.println();
        }


    }

    // Метод сортировки списка книг по разным критериям.
    private void SortBooks(ArrayList<Book> list, String s) {

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
                            public double compare(Book o1, Book o2) {
                                return (o1.price - (o2.price);
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

    private Book FindBook(ArrayList<Book> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Book found = new Book("author", "title", "publisher", 0, 0, 0, 1);
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
                found = b;
                wasFound = true;
            }
        }

        if (!wasFound) {
            System.out.println("No such book in store, sir");
            System.out.println();
        }
        return found;

    }

}

