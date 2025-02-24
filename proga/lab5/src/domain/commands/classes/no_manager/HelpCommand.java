package domain.commands.classes.no_manager;

import java.util.LinkedHashMap;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;

/**
 * Класс {@class HelpCommand} реализует команду справки.
 * <p> 
 * Этот класс исполняет команду справки и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class HelpCommand implements Command, NoArgsCommand {
    private LinkedHashMap<String, Command> commands;
    private String commandName;

    /**
     * Конструктор класса {@class HelpCommand}.
     * 
     * @param commands
     */
    public HelpCommand(LinkedHashMap<String, Command> commands) {
        this.commands = commands;
        commandName = "help";
    }

    /**
     * Исполнение команды.
     * 
     * @param arg
     * @throws IncorrectInputException 
     */
    @Override
    public void execute(String arg) throws IncorrectInputException {
        checkIfNoArg(arg, commandName);
        for (Command value : commands.values()) {
            value.describe();
        }
    }

    /**
     * Описание того, что делает команда
     */
    @Override
    public void describe() {
        System.out.println("help : вывести справку по доступным командам");
    }
}
