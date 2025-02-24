package api.input_entities_api.types_builder;

import api.input_entities_api.exceptions.IncorrectInputException;

public interface NumberParameterBuilder {
    public void reset();
    public void setValue(String valueString) throws IncorrectInputException;
}
