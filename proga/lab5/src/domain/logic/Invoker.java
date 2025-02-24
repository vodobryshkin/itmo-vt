package domain.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.exceptions.EmptyCollectionException;
import domain.commands.exceptions.StackRepeatException;
import domain.commands.interfaces.Command;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс {@class Invoker} перенаправляет команды на выполнение.
 * <p> 
 * Этот класс исполняет переданную в него команду.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class Invoker {
    Map<String, Command> commands;

    /**
     * Конструктор класса {@class Invoker}.
     * 
     * @param commands
     */
    public Invoker(LinkedHashMap<String, Command> commands) {
        this.commands = commands;
    }

    public void commandExecute(String[] arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException {
        commands.get(arg[0]).execute(arg[1]);
    }
}
