package domain.commands.classes.no_manager;

import api.input.classes.input_manager.InputManager;
import api.input.classes.input_strategies.FileInput;
import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.exceptions.StackRepeatException;
import domain.commands.interfaces.Command;
import domain.logic.ExecutingStack;

/**
 * Класс {@class ExecuteScriptCommand} реализует команду исполнения скрипта из файла и описывает её.
 * <p> 
 * Этот класс исполняет команду исполнения скрипта из файла и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class ExecuteScriptCommand implements Command {
    private InputManager inputManager;
    private ExecutingStack executingStack;
    
    /**
     * Конструктор класса {@class ExecuteScriptCommand}.
     * 
     * @param inputManager
     */
    public ExecuteScriptCommand(InputManager inputManager, ExecutingStack executingStack) {
        this.inputManager = inputManager;
        this.executingStack = executingStack;
    }

    /**
     * Исполнение команды с заданным аргументом.
     * 
     * @param arg
     * @throws StackRepeatException 
     * @throws IncorrectInputException 
          */
         @Override
        public void execute(String arg) throws StackRepeatException, IncorrectInputException {
        if (executingStack.isEmpty()) {
            executingStack.push(arg);
            inputManager.setInputStrategy(new FileInput(arg));
        } else {
            if (!executingStack.inStack(arg)) {
                executingStack.push(arg);
                inputManager.setInputStrategy(new FileInput(arg));
            } else {
                throw new StackRepeatException("Ошибка: повторное выполнение скрипта " + arg + " приведет к зацикливанию программы. ");
            }
        }
    }

    /**
     * Описание того, что делает команда
     */
    @Override
    public void describe() {
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }
}
