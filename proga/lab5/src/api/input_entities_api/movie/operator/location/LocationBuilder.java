package api.input_entities_api.movie.operator.location;

import api.input.classes.input_manager.InputManager;
import api.input_entities_api.exceptions.IncorrectInputException;
import api.input_entities_api.movie.operator.location.x.XBuilderDirector;
import api.input_entities_api.movie.operator.location.y.YBuilderDirector;
import api.input_entities_api.movie.operator.location.z.ZBuilderDirector;
import api.input_entities_api.types_builder.ThreeDimVectorBuilder;
import entities.classes.Location;

/**
 * Строитель (Builder) для создания объекта Location (трехмерный вектор).
 * Реализует интерфейс ThreeDimVectorBuilder.
 */
public class LocationBuilder implements ThreeDimVectorBuilder {
    private Location location;
    private InputManager inputManager;
    private XBuilderDirector xBuilderDirector;
    private YBuilderDirector yBuilderDirector;
    private ZBuilderDirector zBuilderDirector;
    
    /**
     * Конструктор класса LocationBuilder.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public LocationBuilder(InputManager inputManager) {
        this.inputManager = inputManager;

        location = new Location();
        xBuilderDirector = new XBuilderDirector(this.inputManager);
        yBuilderDirector = new YBuilderDirector(this.inputManager);
        zBuilderDirector = new ZBuilderDirector(this.inputManager);
    }

    /**
     * Сбрасывает объект Location к новому экземпляру.
     */
    @Override
    public void reset() {
        location = new Location();
    }

    /**
     * Получает и устанавливает координату X для Location, используя XBuilderDirector.
     *
     * @throws IncorrectInputException Если введенное значение координаты X некорректно.
     */
    @Override
    public void inputX() throws IncorrectInputException {
        location.setX(xBuilderDirector.getX());
    }

    /**
     * Получает и устанавливает координату Y для Location, используя YBuilderDirector.
     *
     * @throws IncorrectInputException Если введенное значение координаты Y некорректно.
     */
    @Override
    public void inputY() throws IncorrectInputException {
        location.setY(yBuilderDirector.getY());
    }

    /**
     * Получает и устанавливает координату Z для Location, используя ZBuilderDirector.
     *
     * @throws IncorrectInputException Если введенное значение координаты Z некорректно.
     */
    @Override
    public void inputZ() throws IncorrectInputException {
        location.setZ(zBuilderDirector.getZ());
    }

    /**
     * Возвращает созданный объект Location.
     *
     * @return Созданный объект Location.
     */
    public Location getLocation() {
        return location;
    }
}
