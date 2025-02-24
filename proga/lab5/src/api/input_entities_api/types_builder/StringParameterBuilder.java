package api.input_entities_api.types_builder;

import api.input_entities_api.exceptions.IncorrectInputException;

public interface StringParameterBuilder {
    public void reset();
    public void setString(String valueString) throws IncorrectInputException;
}
