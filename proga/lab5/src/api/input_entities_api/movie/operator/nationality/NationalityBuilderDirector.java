package api.input_entities_api.movie.operator.nationality;

import api.input.classes.input_manager.InputManager;
import api.input.classes.input_strategies.KeyboardInput;
import api.input_entities_api.exceptions.IncorrectInputException;
import entities.enums.Country;

public class NationalityBuilderDirector {
    private InputManager inputManager;
    private NationalityBuilder nationalityBuilder;

    public NationalityBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        nationalityBuilder = new NationalityBuilder();
    }

    public Country getNationality() throws IncorrectInputException {
        nationalityBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле nationality (доступные варианты: FRANCE, SPAIN, CHINA, VATICAN) (поле operator).");
            }
            nationalityBuilder.setValue(inputManager.readNext());
            return nationalityBuilder.getNationality();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getNationality();
            }
        }
    }
}