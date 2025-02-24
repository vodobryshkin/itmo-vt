package domain.commands.classes.manager;

import api.input_entities_api.exceptions.IncorrectInputException;
import domain.commands.interfaces.Command;
import domain.commands.interfaces.IntInputable;
import domain.logic.CollectionManager;
import repository.exceptions.KeyNotFoundException;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 *  Класс, реализующий команду обновления элемента коллекции, ID которого равен заданному.
 */
public class UpdateCommand implements Command, IntInputable {
    private CollectionManager collectionManager;

    /**
     * Конструктор класса UpdateCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду обновления элемента коллекции по ID.
     * @param arg Аргумент команды - ID элемента для обновления.
     * @throws KeyNotFoundException если элемент с указанным ID не найден.
     * @throws IncorrectInputException если аргумент не является целым числом или введены некорректные данные.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int id = tryToInt(arg);
        collectionManager.update(id);
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public void describe() {
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
    }

}
