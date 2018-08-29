
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


    public static void main(String[] args) throws IOException {
        // Создаём файлы и папки магазина, корзины и домашней библиотеки.
        Storage stor = new Storage();
        BookService bsrv = new BookService();
        stor.createFolders();
        stor.createFiles();

        // Заполняем книгохранилище магазина если оно пустое
        if (stor.getStoreBooks().length() == 0) {
            stor.writeFile(bsrv.fillStore(), stor.getStoreBooks());
        }


        // Заполняем домашнюю библиотеку если она пуста
        if (stor.getHomeLibrary().length() == 0) {
            stor.writeFile(bsrv.fillLibrary(), stor.getHomeLibrary());
        }

        //Отображаем меню и обрабатываем команды пользователя.
        Menu menu = new Menu();
        UserInputReader input = new UserInputReader();
        do {
            System.out.println("Welcome to Black Books, please introduce yourself");
            String name = input.askInput("Enter your name, Sir");
            User user = new User(name);
            System.out.println("Hello, "  + user.name + ", how may I be of service?\n" +
                    "Use \"Show menu\" for commands list.");
            String item = input.askInput("Input command");
            menu.chooseMenuItem(item);
        } while (true);
    }


//        System.out.println("Welcome to Black Books, please introduce yourself");
//        String name;
//        int age;
//        boolean sex;
//        double money;
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
//        System.out.println("Type \"commands\" for commands list");
//        System.out.println();

//        // блок команд
//        while (true) {
////            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Input command");
//            String command = reader.readLine();
//
//            switch (command.toLowerCase()) {
//                case "commands":
//                    System.out.println("Show store — displays the list of all books in store");
//                    System.out.println("Show basket — displays books currently in your basket");
//                    System.out.println("Show library — displays the list of all books in your home library");
//                    System.out.println("Refill store — refills shop's repository with new books");
//                    System.out.println("Refill library — refills your library");
//                    System.out.println("Add book — adds new book to specified location");
//                    System.out.println("Remove book — removes selected book from specified location");
//                    System.out.println("Sort books — sorts all books in depository by user defined criteria");
//                    System.out.println("Find book — searches for a certain book in shop's depository");
//                    System.out.println("Add to basket — adds certain book to your basket");
////                    System.out.println("Remove from basket — removes certain book from your basket");
////                    System.out.println("Clear basket — removes all books from your basket");
////                    System.out.println("Cash out — pays for all the books in your basket and" +
////                            " sends them to your home library ");
//                    System.out.println("Exit — leave Black Books");
//                    System.out.println();
//                    break;
//
//                case "show store":
//
//                    try {
//                        if (storeBooks.length() != 0) {
//                            ArrayList<Book> temp = readFile(storeBooks);
//                            System.out.println("Books currently in store: ");
//                            for (Book b : temp) {
//                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
//                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
//                                        + b.pages + "/ Price: "
//                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
//                            }
//                        } else System.out.println("Store depository is empty");
//                    } catch (EOFException e) {
//                        break;
//                    }
//                    break;
//
//                case "show basket":
//
//                    try {
//                        if (basket.length() != 0) {
//                            ArrayList<Book> temp = readFile(basket);
//                            System.out.println("Books currently in basket: ");
//                            double totalPrice = 0;
//                            for (Book b : temp) {
//                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
//                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
//                                        + b.pages + "/ Price: "
//                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
//                                totalPrice += (b.price * b.quantity);
//                            }
//                            DecimalFormat numberFormat = new DecimalFormat("#.00");
//                            System.out.println("Total price of all books in your basket is: "
//                                    + numberFormat.format(totalPrice) + " Rub.");
//                        } else System.out.println("Your basket is empty");
//                    } catch (EOFException e) {
//                        break;
//                    }
//                    break;
//
//                case "show library":
//
//                    try {
//
//                        if (homeLibrary.length() != 0) {
//                            ArrayList<Book> temp = readFile(homeLibrary);
//                            System.out.println("Books currently in your library: ");
//                            for (Book b : temp) {
//                                System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
//                                        + b.publisher + "/ Year: " + b.year + "/ Number of pages: "
//                                        + b.pages + "/ Price: "
//                                        + b.price + " Rub." + "/ Quantity: " + b.quantity);
//                            }
//                        } else System.out.println("Your library is empty");
//                    } catch (EOFException e) {
//                        break;
//                    }
//                    break;
//
//                case "refill store":
//                    try (FileOutputStream fos = new FileOutputStream(storeBooks);
//                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                        for (Book b : storeList) {
//                            oos.writeObject(b);
//                        }
//                    }
//                    System.out.println("Shop's depository refilled");
//                    break;
//
//                case "refill library":
//                    try (FileOutputStream fos = new FileOutputStream(homeLibrary);
//                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                        for (Book b : homeList) {
//                            oos.writeObject(b);
//                        }
//                    }
//                    System.out.println("Home library refilled");
//                    break;
//
//                case "add book":
//                    System.out.println("Where would you like to add a new book? (Store/Library)");
//                    try {
//                        String choice = reader.readLine();
//                        switch (choice.toLowerCase()) {
//                            case "store": {
//                                ArrayList<Book> tempStore = readFile(storeBooks);
//                                try (FileOutputStream fos = new FileOutputStream(storeBooks);
//                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                                    addBook(tempStore);
//                                    for (Book b : tempStore) {
//                                        oos.writeObject(b);
//                                    }
//
//                                } catch (EOFException e) {
//                                    break;
//                                }
//                            }
//                            case "library": {
//                                ArrayList<Book> tempLibrary = readFile(homeLibrary);
//                                try (FileOutputStream fos = new FileOutputStream(homeLibrary);
//                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                                    addBook(tempLibrary);
//                                    for (Book b : tempLibrary) {
//                                        oos.writeObject(b);
//                                    }
//
//                                } catch (EOFException e) {
//                                    break;
//                                }
//                            }
//
//                        }
//                    } catch (IOException e) {
//                        System.out.println("Invalid command");
//                        break;
//                    }
//
//                    break;
//
//                case "remove book":
//                    System.out.println("From where would you like to remove a book? (Store/Library)");
//                    try {
//                        String choice = reader.readLine();
//                        switch (choice.toLowerCase()) {
//                            case "store": {
//                                ArrayList<Book> temp = readFile(storeBooks);
//                                try (FileOutputStream fos = new FileOutputStream(storeBooks);
//                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                                    removeBook(temp);
//                                    for (Book b : temp) {
//                                        oos.writeObject(b);
//                                    }
//                                } catch (EOFException e) {
//                                    break;
//                                }
//                                break;
//                            }
//                            case "library": {
//                                ArrayList<Book> temp = readFile(homeLibrary);
//                                try (FileOutputStream fos = new FileOutputStream(homeLibrary);
//                                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                                    removeBook(temp);
//                                    for (Book b : temp) {
//                                        oos.writeObject(b);
//                                    }
//                                } catch (EOFException e) {
//                                    break;
//                                }
//                                break;
//                            }
//
//                        }
//                    } catch (IOException e) {
//                        System.out.println("Invalid command");
//                        break;
//                    }
//
//
//                    break;
//
//
//                case "sort books":
//                    System.out.println("Enter sorting criteria");
//                    System.out.println("Author/Title/Publisher/Year/Number of pages/Price/Quantity");
//                    String srt = reader.readLine();
//                    try {
//                        ArrayList<Book> temp_sort = readFile(storeBooks);
//                        SortBooks(temp_sort, srt);
//                        for (Book b : temp_sort) {
//                            System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
//                                    + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
//                                    + b.price + " Rub." + "/ Quantity: " + b.quantity);
//                        }
//                    } catch (EOFException e) {
//                        break;
//                    }
//                    break;
//
//                case "find book": {
//                    ArrayList<Book> temp = new ArrayList<>();
//                    temp.add(FindBook(readFile(storeBooks)));
//                    if (temp.size() != 0) {
//                        System.out.println("Here is what we managed to find, sir: ");
//                        for (Book b : temp) {
//                            System.out.println("Author: " + b.author + "/ Title: " + b.title + "/  Publisher: "
//                                    + b.publisher + "/ Year: " + b.year + "/ Number of pages: " + b.pages + "/ Price: "
//                                    + b.price + " Rub.");
//                            System.out.println();
//                        }
//                    }
//                    break;
//                }
//
//
//                case "add to basket": {
//                    Book b1 = (FindBook(readFile(storeBooks)));
//                    System.out.println("How many would you like. Sir?");
//                    int numberToAdd;
//                    while (true) {
//                        try {
//                            System.out.println("Enter quantity");
//                            numberToAdd = Integer.parseInt(reader.readLine());
//                            if (numberToAdd <= 0) {
//                                System.out.println("Minimum quantity is 1");
//                            } else break;
//                        } catch (IOException | NumberFormatException e) {
//                            System.out.println("Invalid quantity");
//                        }
//                    }
//                    b1.quantity = numberToAdd;
//
//                    try (FileOutputStream fos = new FileOutputStream(basket);
//                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                        ArrayList<Book> tempBasket = new ArrayList<>();
//                        ArrayList<Book> tempStore = readFile(storeBooks);
//                        if (basket.length() != 0) {
//                            tempBasket = readFile(basket);
//                            for (Book b : tempBasket) {
//                                if (b.author.toLowerCase().equals(b1.author.toLowerCase())
//                                        && b.title.toLowerCase().equals(b1.title.toLowerCase())
//                                        && b.publisher.toLowerCase().equals(b1.publisher.toLowerCase())
//                                        && (b.year == b1.year) && (b.pages == b1.pages)
//                                        && (b.price == b1.price)) {
//                                    b.quantity += b1.quantity;
//
//                                }
//                            }
//                            Iterator<Book> bookIterator = tempStore.iterator();
//                            while (bookIterator.hasNext()) {
//                                Book b = bookIterator.next();
//                                if (b.author.toLowerCase().equals(b1.author.toLowerCase())
//                                        && b.title.toLowerCase().equals(b1.title.toLowerCase())
//                                        && b.quantity >= 1) {
//                                    b.quantity -= b1.quantity;
//                                }
//
//                                if (b.quantity <= 0) {
//                                    bookIterator.remove();
//                                }
//
//                            }
//                        } else {
//                            tempBasket.add(b1);
//                        }
//                        for (Book b : tempBasket) {
//                            oos.writeObject(b);
//                        }
//
//                    } catch (EOFException e) {
//                        break;
//                    }
//
//                    System.out.println("Book added to your basket");
//                    break;
//                }
//
//
//            }
//            if (command.toLowerCase().equals("exit")) {
//                reader.close();
//                System.out.println("See you again sir, have a nice day!");
//                break;
//            }
//
//
//        }
//



}



