package api.input_entities_api.types_builder;

import api.input_entities_api.exceptions.IncorrectInputException;

public interface PersonBuilder {
    public void reset();
    public void inputName() throws IncorrectInputException;
    public void inputHeight() throws IncorrectInputException;
    public void inputEyeColor() throws IncorrectInputException;
    public void inputHairColor() throws IncorrectInputException;
    public void inputNationality() throws IncorrectInputException;
    public void inputLocation() throws IncorrectInputException;
}
