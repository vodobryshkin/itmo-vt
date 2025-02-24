package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Команда для очистки коллекции.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-24-02
 */
public class ClearCommand implements Command, NoArgsCommand {
    private CollectionManager collectionManager;
    private String commandName;

    /**
     * Конструктор команды.
     *
     * @param collectionManager объект CollectionManager, который будет выполнять основную логику команды
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandName = "clear";
    }

    /**
     * Выполняет команду очистки коллекции.
     *
     * @param arg аргумент команды (должен быть пустым, так как команда не принимает аргументов)
     * @throws KeyNotFoundException если возникла ошибка при очистке коллекции
     * @throws IncorrectInputException если аргумент команды некорректен
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        collectionManager.clear();
    }

    /**
     * Описание команды.
     */
    @Override
    public void describe() {
        System.out.println("clear : очистить коллекцию");
    }
}