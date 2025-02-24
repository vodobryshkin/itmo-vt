package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;
/**
 * Класс InfoCommand реализует команду для вывода информации о коллекции.
 * Команда не принимает аргументов и выводит данные о типе коллекции, дате инициализации и количестве элементов.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class InfoCommand implements Command, NoArgsCommand {
    private CollectionManager collectionManager;
    private String commandName;

    /**
     * Конструктор класса InfoCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandName = "info";
    }

    /**
     * Выполняет команду "info". Проверяет отсутствие аргументов и выводит информацию о коллекции.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        collectionManager.info();
    }

    /**
     * Выводит описание команды "info".
     */
    @Override
    public void describe() {
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}