package api.input_entities_api.types_builder;

import api.input_entities_api.exceptions.IncorrectInputException;

public interface MovieBuilder {
    public void reset();
    public void inputName() throws IncorrectInputException;
    public void inputCoordinates() throws IncorrectInputException;
    public void inputOscarsCount() throws IncorrectInputException;
    public void inputGenre() throws IncorrectInputException;
    public void inputMpaaRating() throws IncorrectInputException;
    public void inputOperator() throws IncorrectInputException;
}
