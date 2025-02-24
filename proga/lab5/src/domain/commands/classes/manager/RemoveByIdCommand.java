package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.IntInputable;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс RemoveByIdCommand реализует команду для удаления элемента коллекции по его идентификатору (id).
 * Команда принимает один аргумент — целочисленный идентификатор элемента, который необходимо удалить.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class RemoveByIdCommand implements Command, IntInputable {
    private CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveByIdCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду "remove_by_id". Удаляет элемент коллекции по указанному идентификатору.
     *
     * @param arg аргумент команды (идентификатор элемента, который нужно удалить)
     * @throws KeyNotFoundException если элемент с указанным идентификатором не найден
     * @throws IncorrectInputException если введены некорректные данные (например, аргумент не является числом)
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int id = tryToInt(arg);
        collectionManager.remove_by_id(id);
    }

    /**
     * Выводит описание команды "remove_by_id".
     */
    @Override
    public void describe() {
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}