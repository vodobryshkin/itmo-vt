package domain.commands.interfaces;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.exceptions.EmptyCollectionException;
import domain.commands.exceptions.StackRepeatException;
import repository.exceptions.KeyNotFoundException;

/**
 * Интерфейс {@interface Command} создаёт шаблон для реализации команд, как классов.
 * <p> 
 * Этот интерфейс вводит описание того, как исполнять команду и описывать её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public interface Command {
    /**
     * Исполнение команды с заданным аргументом.
     * 
     * @param arg
     */
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException;


    /**
     * Описание того, что делает команда.
     */
    public void describe();
}
