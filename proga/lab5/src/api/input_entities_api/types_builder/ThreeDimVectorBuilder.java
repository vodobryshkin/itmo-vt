package api.input_entities_api.types_builder;

import api.input_entities_api.exceptions.IncorrectInputException;

public interface ThreeDimVectorBuilder extends VectorBuilder {
    public void inputZ() throws IncorrectInputException;
}
