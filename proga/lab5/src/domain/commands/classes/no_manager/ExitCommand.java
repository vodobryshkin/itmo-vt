package domain.commands.classes.no_manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.NoArgsCommand;

/**
 * Класс {@class ExitCommand} реализует команду выхода из программы.
 * <p> 
 * Этот класс исполняет команду выхода из программы и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class ExitCommand implements Command, NoArgsCommand  {    
    private String commandName;

    public ExitCommand() {
        commandName = "exit";
    }
    /**
     * Исполнение команды с заданным аргументом.
     * 
     * @param arg
     * @throws IncorrectInputException 
     */
    @Override
    public void execute(String arg) throws IncorrectInputException {
        checkIfNoArg(arg, commandName);
        System.exit(0);
    }

    /**
     * Описание того, что делает команда
     * @return
     */
    @Override
    public void describe() {
        System.out.println("exit : завершить программу (без сохранения в файл)");
    }
}
