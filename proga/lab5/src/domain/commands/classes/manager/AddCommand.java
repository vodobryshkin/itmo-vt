package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 * Команда для добавления нового элемента в коллекцию.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class AddCommand implements Command, NoArgsCommand {
    private CollectionManager receiver;
    private String commandName;

    /**
     * Конструктор команды.
     *
     * @param receiver объект CollectionManager, который будет выполнять основную логику команды
     */
    public AddCommand(CollectionManager receiver) {
        this.receiver = receiver;
        commandName = "add";
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     *
     * @param arg аргумент команды (должен быть пустым, так как команда не принимает аргументов)
     * @throws KeyNotFoundException если возникла ошибка при добавлении элемента
     * @throws IncorrectInputException если аргумент команды некорректен
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        receiver.add();
    }

    /**
     * Описание команды.
     */
    @Override
    public void describe() {
        System.out.println("add {element} : добавить новый элемент в коллекцию");
    }
}