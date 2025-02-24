package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.exceptions.EmptyCollectionException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс MaxByCreationDateCommand реализует команду для вывода объекта коллекции с максимальным значением поля creationDate.
 * Команда не принимает аргументов и выводит объект, у которого значение creationDate является наибольшим.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class MaxByCreationDateCommand implements Command, NoArgsCommand {
    private CollectionManager collectionManager;
    private String commandName;

    /**
     * Конструктор класса MaxByCreationDateCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public MaxByCreationDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandName = "max_by_creation_date";
    }

    /**
     * Выполняет команду "max_by_creation_date". Проверяет отсутствие аргументов и выводит объект с максимальным значением creationDate.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     * @throws EmptyCollectionException если коллекция пуста
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException {
        checkIfNoArg(arg, commandName);
        collectionManager.max_by_creation_date();
    }

    /**
     * Выводит описание команды "max_by_creation_date".
     */
    @Override
    public void describe() {
        System.out.println("max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным");
    }
}