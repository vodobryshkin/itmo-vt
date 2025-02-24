package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 *  Класс, реализующий команду удаления из коллекции всех элементов, меньших, чем заданный.
 */
public class RemoveLowerCommand implements Command, NoArgsCommand {
    private CollectionManager collectionManager;
    private String commandName;

    /**
     * Конструктор класса RemoveLowerCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandName = "remove_lower";
    }

    /**
     * Выполняет команду удаления элементов, меньших, чем заданный.
     * @param arg Аргумент команды (не используется, но проверяется на отсутствие).
     * @throws KeyNotFoundException если ключ не найден (не применимо, но проброшено из CollectionManager).
     * @throws IncorrectInputException если введены некорректные данные или передан аргумент.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        collectionManager.remove_lower();
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public void describe() {
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }

}
