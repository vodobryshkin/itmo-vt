/**
 * Класс для чтения данных о фильмах из CSV-файла.
 * Реализует интерфейсы {@link Reader} и {@link FileWorker}.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package repository.csv.adapters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.classes.Movie;
import repository.exceptions.FileNotFoundException;
import repository.interfaces.FileWorker;
import repository.interfaces.Reader;

public class CsvReader implements Reader, FileWorker {
    private ArrayList<Movie> collection;

    /**
     * Конструктор по умолчанию. Инициализирует пустую коллекцию фильмов.
     */
    public CsvReader() {
        collection = new ArrayList<Movie>();
    }

    /**
     * Читает данные из CSV-файла и возвращает коллекцию фильмов.
     * 
     * @return Коллекция объектов {@link Movie}, прочитанных из файла.
     */
    @Override
    public ArrayList<Movie> read() {
        try {
            String pathString = getPath();
            try (BufferedReader reader = new BufferedReader(new FileReader(pathString))) {
                String line;
                reader.readLine(); // Пропуск заголовка

                while ((line = reader.readLine()) != null) {
                    collection.add(Movie.parseMovie(line));
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return collection;
    }

    /**
     * Возвращает путь к файлу, заданный через переменную окружения PATH.
     * 
     * @return Путь к файлу.
     * @throws FileNotFoundException Если переменная окружения PATH не задана.
     */
    @Override
    public String getPath() throws FileNotFoundException {
        String path = System.getenv("PATH");

        if (path == null) {
            throw new FileNotFoundException("Ошибка: переменная окружения с названием входного файла не задана.");
        }

        return path;
    }
}