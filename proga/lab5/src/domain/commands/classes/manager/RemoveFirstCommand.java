package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.exceptions.EmptyCollectionException;
import domain.commands.interfaces.Command;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 *  <p>
 *  Класс, реализующий команду удаления первого элемента из коллекции.
 *  </p>
 */
public class RemoveFirstCommand implements Command {
    private CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveFirstCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public RemoveFirstCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду удаления первого элемента из коллекции.
     * @param arg Аргумент команды (не используется).
     * @throws KeyNotFoundException если ключ не найден (не применимо, но проброшено из CollectionManager).
     * @throws IncorrectInputException если введены некорректные данные (не применимо, но проброшено из CollectionManager).
     * @throws EmptyCollectionException если коллекция пуста.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException {
        collectionManager.remove_first();
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public void describe() {
        System.out.println("remove_first : удалить первый элемент из коллекции");
    }

}
