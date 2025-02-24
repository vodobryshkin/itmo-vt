package client.classes;

import java.util.LinkedHashMap;

import api.input.classes.input_manager.InputManager;
import api.input.classes.input_strategies.KeyboardInput;
import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.classes.manager.*;
import domain.commands.classes.no_manager.*;
import domain.commands.exceptions.*;
import domain.commands.interfaces.Command;
import domain.logic.CollectionManager;
import domain.logic.ExecutingStack;
import domain.logic.Invoker;
import repository.csv.CsvRepository;
import repository.exceptions.KeyNotFoundException;

/**
 * Класс {@class Client} предоставляет доступ к клиентскому уровню приложения.
 * <p> 
 * Этот интерфейс умеет запускать работу программы.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class Client {
    private Invoker invoker;
    private LinkedHashMap<String, Command> commands;
    private InputManager inputManager;
    private CollectionManager collectionManager;
    private ExecutingStack executingStack;
    
    /**
     * Конструктор класса {@class Client}.
     */
    public Client() {
        commands = new LinkedHashMap<>();
        invoker = new Invoker(commands);
        inputManager = new InputManager();
        collectionManager = new CollectionManager(new CsvRepository(), inputManager);
        executingStack = new ExecutingStack();
    }
    /**
     * Запуск программы.
     * @throws IncorrectInputException 
     * @throws KeyNotFoundException 
     */
    public void run() throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(inputManager, executingStack));
        commands.put("exit", new ExitCommand());
        commands.put("remove_at", new RemoveAtCommand(collectionManager));
        commands.put("remove_first", new RemoveFirstCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("max_by_creation_date", new MaxByCreationDateCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commands.put("print_unique_mpaa_rating", new PrintUniqueMpaaRatingCommand(collectionManager));

        while (inputManager.hasNext()) {
            String line = inputManager.readNext();
            String[] tokens = line.split(" ");
        
            if (tokens.length == 1) {
                line += " null";
                tokens = line.split(" ");
            }
                
            try {
                if (!tokens[0].isEmpty()) {
                    invoker.commandExecute(tokens);
                }
            } catch (NullPointerException e) {
                if (!tokens[0].isEmpty()) {
                    System.out.println(tokens[0] + ": такой команды не существует. Повторите попытку ввода.");
                }
            } catch (IncorrectInputException e) {
                if (inputManager.toString() != "FileInput")
                {
                    System.out.println(e.getMessage() + "Повторите попытку ввода.");
                } else {
                    System.out.println(e.getMessage() + "Действие скрипта завершено.");
                }
                
            } catch (KeyNotFoundException e) {
                if (inputManager.toString() != "FileInput")
                {
                    System.out.println(e.getMessage() + "Повторите попытку ввода.");
                } else {
                    System.out.println(e.getMessage() + "Действие скрипта завершено.");
                }
                
            } catch (EmptyCollectionException e) {
                if (inputManager.toString() != "FileInput")
                {
                    System.out.println(e.getMessage() + "Повторите попытку ввода.");
                } else {
                    System.out.println(e.getMessage() + "Действие скрипта завершено.");
                }
                
            }  catch (StackRepeatException e) {
                System.out.println(e.getMessage() + "Действие скрипта завершено.");
            } 

            if (!inputManager.hasNext() && inputManager.toString() == "FileInput") {
                inputManager.setInputStrategy(new KeyboardInput());
            }
        }
    }
}
