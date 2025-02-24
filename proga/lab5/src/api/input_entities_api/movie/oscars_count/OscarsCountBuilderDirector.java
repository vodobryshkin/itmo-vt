package api.input_entities_api.movie.oscars_count;

import api.input.classes.input_manager.InputManager;
import api.input.classes.input_strategies.KeyboardInput;
import api.input_entities_api.exceptions.IncorrectInputException;

/**
 * OscarsCountBuilderDirector управляет процессом получения количества Оскаров.
 * Использует OscarsCountBuilder и InputManager для ввода данных, взаимодействуя с пользователем
 * и обрабатывая возможные ошибки ввода.
 * 
 * Автор: Добрышкин Владимир (vodobryshkin)
 * Версия: 1.0
 * Дата: 2025-19-02
 */
public class OscarsCountBuilderDirector {
    private InputManager inputManager;
    private OscarsCountBuilder oscarsCountBuilder;

    /**
     * Конструктор класса OscarsCountBuilderDirector.
     *
     * @param inputManager экземпляр InputManager, используемый для чтения ввода.
     */
    public OscarsCountBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        oscarsCountBuilder = new OscarsCountBuilder();
    }

    /**
     * Получает количество Оскаров от пользователя, запрашивая ввод и обрабатывая возможные ошибки.
     *
     * @return текущее значение количества Оскаров или null, если ввод не удался.
     * @throws IncorrectInputException если возникла ошибка при обработке ввода.
     */
    public Long getOscarsCount() throws IncorrectInputException {
        oscarsCountBuilder.reset();

        try {
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println("Введите поле oscarsCount.");
            }
            oscarsCountBuilder.setValue(inputManager.readNext());
            return oscarsCountBuilder.getOscarsCount();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + " Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());
                return null;
            } else {
                System.out.println(e.getMessage() + " Повторите попытку ввода.");
                return getOscarsCount();
            }
        }
    }
}
