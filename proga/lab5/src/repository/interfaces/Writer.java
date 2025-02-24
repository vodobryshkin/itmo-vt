package repository.interfaces;

import repository.csv.CsvRepository;
import repository.exceptions.KeyNotFoundException;


/**
 * Интерфейс, который записывает в файл.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface Writer {
    public void write(CsvRepository csvRepository) throws KeyNotFoundException ;
}
