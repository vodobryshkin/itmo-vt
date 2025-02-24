package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.IntInputable;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс RemoveAtCommand реализует команду для удаления элемента коллекции по указанному индексу.
 * Команда принимает один аргумент — целочисленный индекс элемента, который необходимо удалить.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class RemoveAtCommand implements Command, IntInputable {
    private CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveAtCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public RemoveAtCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду "remove_at". Удаляет элемент коллекции по указанному индексу.
     *
     * @param arg аргумент команды (индекс элемента, который нужно удалить)
     * @throws KeyNotFoundException если элемент с указанным индексом не найден
     * @throws IncorrectInputException если введены некорректные данные (например, аргумент не является числом)
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int index = tryToInt(arg);
        collectionManager.remove_at(index);
    }

    /**
     * Выводит описание команды "remove_at".
     */
    @Override
    public void describe() {
        System.out.println("remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)");
    }
}