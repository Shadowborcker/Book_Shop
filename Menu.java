import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Menu {


    private int input;

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    User user;

    public void greetingsMenu() throws IOException {
        UserInputReader input = new UserInputReader();
        System.out.println("Welcome to Black Books, please introduce yourself");
        String name = input.askString("Enter your name, Sir");
        user = new User(name);
        System.out.println("Hello, " + user.getName() + " , your balance is "
                + user.getMoney() + "Rub." + ", how may I be of service?\n" +
                "Use \"Show menu\" for commands list.");
    }

    public void chooseMenuItem(String input) throws IOException {
        HashMap<String, MenuItem> menumap = new HashMap<>();
        menumap.put("show menu", new MenuItemShowMenu());
        menumap.put("show books", new MenuItemShowBooks());
        menumap.put("refill", new MenuItemRefill());
        menumap.put("add book", new MenuItemAddBook());
        menumap.put("remove book", new MenuItemRemoveBook());
        menumap.put("sort", new MenuItemSort());
        menumap.put("search", new MenuItemSearch());
        menumap.put("cash out", new MenuItemCashOut());
        menumap.put("exit", new MenuItemExit());
        menumap.get(input.toLowerCase()).select();
    }


    interface MenuItem {
        String description();

        void select() throws IOException;
    }


    class MenuItemShowMenu implements MenuItem {
        public String description() {
            return "Showing all available commands";
        }

        public void select() {

            System.out.println("Show books — displays the list of all books in specified location\n" +
                    "Refill  — refills specified book storage\n" +
                    "Add books — adds books to specified location\n" +
                    "Remove books — removes selected books from specified location\n" +
                    "Sort — sorts all books by user defined criteria\n" +
                    "Search — searches for a certain book\n" +
                    "Cash out — pays for all the books in your basket and\n" +
                    " sends them to your home library\n" +
                    "Exit — closes Black Books");
        }

    }

    class MenuItemExit implements MenuItem {
        public String description() {
            return "Terminating program.\n" +
                    "See you again, sir!";
        }

        public void select() {
            System.out.println(description());
            System.exit(0);
        }

    }


    class MenuItemShowBooks implements MenuItem {
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        String choice;
        File f = new File("Temp");

        public File submenu() throws IOException {
            String sub = reader.askString("Where would you like to look?\n" +
                    "Store\\Basket\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
                }
                case "basket": {
                    choice = "basket";
                    f = stor.getBasket();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Showing all books in " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            double totalprice = 0;

            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                if (temp.size() != 0) {
                    System.out.println(description());
                    for (Book b : temp) {
                        System.out.println(b.toString());
                        totalprice += b.getPrice() * b.getQuantity();
                    }
                    if (f.equals(stor.getBasket())) {
                        System.out.println("For a total price of " + numberFormat.format(totalprice) + "Rub.");
                    }
                } else System.out.println(choice + " is empty, Sir");
            } else System.out.println(choice + " is empty, Sir");
            System.out.println();

        }
    }

    class MenuItemRefill implements MenuItem {
        BookService bsrv = new BookService();
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        ArrayList<Book> torefill = new ArrayList<>();
        File f = new File("Temp");
        String choice;

        private File submenu() throws IOException {
            String sub = reader.askString("What depository would you like to refill?\n" +
                    "Store\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    torefill = bsrv.fillStore();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    torefill = bsrv.fillLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Refilling " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            stor.writeFile(torefill, f);
            System.out.println(description());
            System.out.println(choice + " is refilled");
        }
    }

    class MenuItemAddBook implements MenuItem {
        BookService bsrv = new BookService();
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = new File("Temp");
        String choice;

        private File submenu() throws IOException {
            String sub = reader.askString("Where would you like to add a new book?\n" +
                    "Store\\Basket\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
                }
                case "basket": {
                    choice = "basket";
                    f = stor.getBasket();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Adding to " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                System.out.println(description());
                bsrv.addBook(temp);
                stor.writeFile(temp, f);
            } else {
                ArrayList<Book> temp = new ArrayList<>();
                System.out.println(description());
                bsrv.addBook(temp);
                stor.writeFile(temp, f);
            }
            System.out.println("Book(s) added to " + choice);
            System.out.println();
        }
    }

    class MenuItemRemoveBook implements MenuItem {
        BookService bsrv = new BookService();
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = new File("Temp");
        String choice;

        private File submenu() throws IOException {
            String sub = reader.askString("From where would you like to remove books?\n" +
                    "Store\\Basket\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
                }
                case "basket": {
                    choice = "basket";
                    f = stor.getBasket();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Removing books from " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                if (temp.size() != 0) {
                    System.out.println(description());
                    bsrv.removeBook(temp);
                    stor.writeFile(temp, f);
                } else System.out.println(choice + " is empty, Sir");
            } else System.out.println(choice + " is empty, Sir");
            System.out.println();


        }
    }

    class MenuItemSort implements MenuItem {
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = new File("Temp");
        String choice;

        private File submenu() throws IOException {
            String sub = reader.askString("Where would you like to sort the books?\n" +
                    "Store\\Basket\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
                }
                case "basket": {
                    choice = "basket";
                    f = stor.getBasket();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Sorting all books in " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            BookService serv = new BookService();

            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                if (temp.size() != 0) {
                    String sorttype = reader.askString("Choose sorting criteria\n" +
                            "Author\\Title\\Publisher\\Year\\Pages\\Price\\Quantity");
                    serv.sortBooks(temp, sorttype);
                    System.out.println(description());
                    for (Book b : temp) {
                        System.out.println(b.toString());
                    }
                    System.out.println(choice + " is sorted by " + sorttype);
                    System.out.println();
                } else System.out.println(choice + " is empty, Sir");
            } else System.out.println(choice + " is empty, Sir");
            System.out.println();

        }

    }

    class MenuItemSearch implements MenuItem {
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = new File("Temp");
        String choice;

        private File submenu() throws IOException {
            String sub = reader.askString("Where would you like to search?\n" +
                    "Store\\Basket\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
                }
                case "basket": {
                    choice = "basket";
                    f = stor.getBasket();
                    break;
                }
                case "library": {
                    choice = "library";
                    f = stor.getHomeLibrary();
                    break;
                }
                default:
                    choice = "store";
                    f = stor.getStoreBooks();
                    break;
            }
            return f;
        }

        public String description() {
            return "Searching for a book in " + choice;
        }

        public void select() throws IOException {
            f = submenu();
            BookService serv = new BookService();

            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                if (temp.size() != 0) {
                    temp = serv.findBook(temp);
                    System.out.println(description());
                    System.out.println("Here is what we managed to find in " + choice);
                    for (Book b : temp) {
                        System.out.println(b.toString());
                    }
                } else System.out.println(choice + " is empty, Sir");
            } else System.out.println(choice + " is empty, Sir");
            System.out.println();

        }

    }

    class MenuItemCashOut implements MenuItem {
        Storage stor = new Storage();
        double totalprice = 0;

        public String description() {
            return "Paying for all the books in basket";
        }

        public void select() {
            if (stor.getBasket().length() != 0) {
                ArrayList<Book> bskt = stor.readFile(stor.getBasket());
                ArrayList<Book> str = stor.readFile(stor.getStoreBooks());
                ArrayList<Book> lib = stor.readFile(stor.getHomeLibrary());
                if (bskt.size() != 0) {
                    System.out.println(description());
                    for (Book b : bskt) {
                        totalprice += (b.getPrice() * b.getQuantity());
                    }
                    if (totalprice <= user.getMoney()) {
                        user.setMoney(user.getMoney() - totalprice);
                        Iterator<Book> storeIterator = str.iterator();
                        for (Book bb : bskt) {
                            while (storeIterator.hasNext()) {
                                Book bs = storeIterator.next();
                                if (bb.equals(bs)
                                        & bs.getQuantity() >= 1) {
                                    bs.setQuantity(bs.getQuantity() - bb.getQuantity());
                                }

                                if (bs.getQuantity() <= 0) {
                                    storeIterator.remove();
                                }
                            }
                            stor.writeFile(str, stor.getStoreBooks());
                            boolean wasfoud = false;
                            for (Book bl : lib) {
                                if (bb.equals(bl)) {
                                    bl.setQuantity(bb.getQuantity() + bl.getQuantity());
                                    wasfoud = true;
                                }
                            }
                            if (wasfoud) {
                                lib.addAll(bskt);
                            }
                            stor.writeFile(lib, stor.getHomeLibrary());
                        }
                        ArrayList<Book> clear = new ArrayList<>();
                        stor.writeFile(clear, stor.getBasket());
                    }
                } else System.out.println("Basket is empty, Sir");


            } else System.out.println("Basket is empty, Sir");
            System.out.println();

        }

    }
}
