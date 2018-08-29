import java.io.*;
import java.util.ArrayList;

public class Storage {

    File storeDir = new File("Store");
    File storeBooks = new File(storeDir, "Depository");
    File userDir = new File("User");
    File basket = new File(userDir, "Basket");
    File homeDir = new File("Home");
    File homeLibrary = new File(homeDir, "Library");

    //Метод создания папок магазина, юзера и дома, а так же файлы книгохранилища, корзины и книгохранилища.
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
            return list;
        }
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
