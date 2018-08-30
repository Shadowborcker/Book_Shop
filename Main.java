
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


    public static void main(String[] args) {
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
        try {
            menu.greetingsMenu();
        } catch (IOException e) {
            System.out.println("Invalid input");
            e.printStackTrace();
        }
        do {
            try {
                String item = input.askString("Input command");
                menu.chooseMenuItem(item);
            } catch (IOException e) {
                System.out.println("Invalid input");
                e.printStackTrace();
                break;
            }

        } while (true);
    }

}



