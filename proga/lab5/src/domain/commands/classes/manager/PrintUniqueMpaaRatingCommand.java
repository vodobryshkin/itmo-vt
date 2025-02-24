package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс PrintUniqueMpaaRatingCommand реализует команду для вывода уникальных значений поля mpaaRating всех элементов коллекции.
 * Команда не принимает аргументов и выводит список уникальных значений поля mpaaRating.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class PrintUniqueMpaaRatingCommand implements Command, NoArgsCommand {
    private CollectionManager collectionManager;
    private String commandName;

    /**
     * Конструктор класса PrintUniqueMpaaRatingCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public PrintUniqueMpaaRatingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandName = "print_unique_mpaa_rating";
    }

    /**
     * Выполняет команду "print_unique_mpaa_rating". Проверяет отсутствие аргументов и выводит уникальные значения поля mpaaRating.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        collectionManager.print_unique_mpaa_rating();
    }

    /**
     * Выводит описание команды "print_unique_mpaa_rating".
     */
    @Override
    public void describe() {
        System.out.println("print_unique_mpaa_rating : вывести уникальные значения поля mpaaRating всех элементов в коллекции");
    }
}