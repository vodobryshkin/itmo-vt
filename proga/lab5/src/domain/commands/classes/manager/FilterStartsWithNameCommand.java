package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Команда для фильтрации элементов коллекции, у которых поле name начинается с заданной подстроки.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-24-02
 */
public class FilterStartsWithNameCommand implements Command {
    private CollectionManager collectionManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду фильтрации элементов коллекции по началу строки в поле name.
     *
     * @param arg подстрока, с которой должно начинаться поле name.
     * @throws KeyNotFoundException если элемент с указанным ключом не найден.
     * @throws IncorrectInputException если ввод некорректен.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        collectionManager.filter_starts_with_name(arg);
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public void describe() {
        System.out.println("filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
    }
}