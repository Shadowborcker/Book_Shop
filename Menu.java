import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {


    private int input;

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public void chooseMenuItem(String input) {
        switch (input.toLowerCase()) {

            case "show menu":
                MenuItemShowMenu showMenu = new MenuItemShowMenu();
                showMenu.print();
                break;
            case "show books":
                MenuItemShowBooks showStore = new MenuItemShowBooks();
                showStore.print();
                break;
            case "refill":
                MenuItemRefill ref = new MenuItemRefill();
                ref.reFill();
                break;

        }

    }

    interface MenuItem {
        String description();
    }

    interface SubMenu {
        File submenu();
    }

    class MenuItemShowMenu implements MenuItem {
        public String description() {
            return "Showing all available commands";
        }

        public void print() {

            System.out.println("Show books — displays the list of all books in specified location\n" +
                    "Refill  — refills specified book storage\n" +
                    "Add book — adds new book to specified location\n" +
                    "Remove book — removes selected book from specified location\n" +
                    "Sort — sorts all books by user defined criteria\n" +
                    "Search — searches for a certain book\n" +
                    "Clear — removes all books from your basket\n" +
                    "Cash out — pays for all the books in your basket and\n" +
                    " sends them to your home library\n" +
                    "Exit — closes Black Books");
        }

    }


    class MenuItemShowBooks implements MenuItem, SubMenu {
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = submenu();
        String choice;
        public File submenu() {
            String sub = reader.askInput("Where would you like to look?\n" +
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

        public void print() {

            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                System.out.println(description());
                for (Book b : temp) {
                    System.out.println(b.toString());
                }
                System.out.println();
            } else System.out.println(choice + " is empty, Sir");

        }
    }
    class MenuItemRefill implements MenuItem, SubMenu {
        BookService bsrv = new BookService();
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = submenu();
        String choice;
        public File submenu() {
            String sub = reader.askInput("What depository would yoi like to refill?\n" +
                    "Store\\Library");
            switch (sub.toLowerCase()) {
                case "store": {
                    choice = "store";
                    f = stor.getStoreBooks();
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
            return "Refilling " + choice;
        }
        public void reFill() {
            stor.writeFile(bsrv.fillStore(), f);
            System.out.println(description());
            System.out.println(choice + "is refilled");
        }
    }

//    class MenuItemAddBook implements MenuItem, SubMenu {
//        UserInputReader reader = new UserInputReader();
//        Storage stor = new Storage();
//        File f = submenu();
//        String choice;
//        public File submenu() {
//            String sub = reader.askInput("Where would you like to add the books?\n" +
//                    "Store\\Basket\\Library");
//            switch (sub.toLowerCase()) {
//                case "store": {
//                    choice = "store";
//                    f = stor.getStoreBooks();
//                    break;
//                }
//                case "basket": {
//                    choice = "basket";
//                    f = stor.getBasket();
//                    break;
//                }
//                case "library": {
//                    choice = "library";
//                    f = stor.getHomeLibrary();
//                    break;
//                }
//                default:
//                    choice = "store";
//                    f = stor.getStoreBooks();
//                    break;
//            }
//            return f;
//        }
//        public String description() {
//            return "Sorting all books in " + choice;
//        }
//    }

    class MenuItemSort implements MenuItem, SubMenu {
        UserInputReader reader = new UserInputReader();
        Storage stor = new Storage();
        File f = submenu();
        String choice;
        public File submenu() {
            String sub = reader.askInput("Where would you like to sort the books?\n" +
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
        public void print() {
            BookService serv = new BookService();

            if (f.length() != 0) {
                ArrayList<Book> temp = stor.readFile(f);
                String sorttype = reader.askInput("Choose sorting criteria\n" +
                        "Author\\Title\\Publisher\\Year\\Pages\\Price\\Quantity");
                serv.sortBooks(temp, sorttype);
                System.out.println(description());
                for (Book b : temp) {
                    System.out.println(b.toString());
                }
                System.out.println(choice + "is sorted by " + sorttype);
                System.out.println();
            } else System.out.println(choice + " is empty, Sir");

        }

    }

}
