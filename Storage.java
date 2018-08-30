import java.io.*;
import java.util.ArrayList;

public class Storage {

    private File storeDir;
    private File storeBooks;
    private File userDir;
    private File basket;
    private File homeDir;
    private File homeLibrary;

    public Storage() {
        storeDir = new File("Store");
        storeBooks = new File(storeDir, "Depository");
        userDir = new File("User");
        basket = new File(userDir, "Basket");
        homeDir = new File("Home");
        homeLibrary = new File(homeDir, "Library");
    }

    public File getStoreBooks() {
        return storeBooks;
    }

    public File getBasket() {
        return basket;
    }

    public File getHomeLibrary() {
        return homeLibrary;
    }

    //Метод создания папок магазина, юзера и дома.
    public void createFolders() {

        if (!storeDir.exists()) {
            storeDir.mkdir();
        }

        if (!userDir.exists()) {
            userDir.mkdir();
        }

        if (!homeDir.exists()) {
            homeDir.mkdir();
        }

    }
    //Метод создания файлов книгохранилища, корзины и библиотеки
    public void createFiles() {
        try {
            if (!storeBooks.exists()) {
                storeBooks.createNewFile();
            }

            if (!basket.exists()) {
                basket.createNewFile();
            }

            if (!homeLibrary.exists()) {
                homeLibrary.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод чтения книг из файла в список.
    public ArrayList<Book> readFile(File f) {
        ArrayList<Book> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                list.add((Book) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return list;
    }

    //Метод записи книг из списка в файл.
    public void writeFile(ArrayList<Book> list, File f) {
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Book b : list) {
                oos.writeObject(b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
