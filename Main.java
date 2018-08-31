
/*
Создаём книжный магазин.
Цель - приложение с консольным интерфейсом, хранящее книги в виде записей в файле на жестком диске.
Функционал условынй - добавление, удаление, обмен и покупка книг.
Работа со списком книг - сортировка, поиск.
*/

import java.io.*;

public class Main {


    public static void main(String[] args) {
        // Создаём файлы и папки магазина, корзины и домашней библиотеки.
        Storage storage = new Storage();
        BookService bookService = new BookService();
        storage.createFolders();
        storage.createFiles();

        // Заполняем книгохранилище магазина если оно пустое
        if (storage.getStoreBooks().length() == 0) {
            storage.write(bookService.fillStore(), storage.getStoreBooks());
        }


        // Заполняем домашнюю библиотеку если она пуста
        if (storage.getHomeLibrary().length() == 0) {
            storage.write(bookService.fillLibrary(), storage.getHomeLibrary());
        }

        //Отображаем меню и обрабатываем команды пользователя.
        Menu menu = new Menu();
        UserInputReader userInputReader = new UserInputReader();
        try {
            menu.greetingsMenu();
        } catch (IOException e) {
            System.out.println("Invalid input");
            e.printStackTrace();
        }
        do {
            try {
                String item = userInputReader.askString("Input command");
                menu.chooseMenuItem(item);
            } catch (IOException e) {
                System.out.println("Invalid input");
                e.printStackTrace();
                break;
            }

        } while (true);
    }

}



