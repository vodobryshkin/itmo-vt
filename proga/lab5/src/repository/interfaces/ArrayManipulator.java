/**
 * Интерфейс для работы с массивом объектов типа Movie.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package repository.interfaces;

import entities.classes.Movie;
import repository.exceptions.KeyNotFoundException;

public interface ArrayManipulator {
    /**
     * Возвращает объект Movie по указанному индексу.
     *
     * @param index индекс элемента в массиве
     * @return объект Movie, находящийся по указанному индексу
     * @throws KeyNotFoundException если элемент с указанным индексом не найден
     */
    public Movie getByIndex(int index) throws KeyNotFoundException;

    /**
     * Возвращает размер массива.
     *
     * @return количество элементов в массиве
     */
    public int getSize();
}