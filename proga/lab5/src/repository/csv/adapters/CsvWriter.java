/**
 * Класс для записи данных в CSV-файл.
 * Реализует интерфейсы {@link Writer} и {@link FileWorker}.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package repository.csv.adapters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import repository.csv.CsvRepository;
import repository.exceptions.FileNotFoundException;
import repository.exceptions.KeyNotFoundException;
import repository.interfaces.FileWorker;
import repository.interfaces.Writer;

public class CsvWriter implements Writer, FileWorker {

    /**
     * Записывает данные из репозитория в CSV-файл.
     * 
     * @param csvRepository Репозиторий, содержащий данные для записи.
     * @throws KeyNotFoundException Если ключ не найден в репозитории.
     */
    @Override
    public void write(CsvRepository csvRepository) throws KeyNotFoundException {
        try {
            String pathString = getPath();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathString))) {
                writer.write("id,name,coordinates_x,coordinates_y,creationDate,oscarsCount,genre,mpaaRating,operator_name,operator_height,operator_eyeColor,operator_hairColor,operator_nationality,operator_location_x,operator_location_y,operator_location_z");
                writer.newLine();
                for (int i = 0; i < csvRepository.getSize(); i++) {
                    String data = csvRepository.getByIndex(i).toCsv();
                    writer.write(data);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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