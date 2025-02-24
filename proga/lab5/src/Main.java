import api.input_entities_api.exceptions.IncorrectInputException;
import client.classes.Client;
import domain.commands.exceptions.EmptyCollectionException;
import domain.commands.exceptions.StackRepeatException;
import repository.exceptions.KeyNotFoundException;

public class Main {
    public static void main(String[] args) throws IncorrectInputException, KeyNotFoundException, EmptyCollectionException, StackRepeatException {
        Client client = new Client();
        client.run();
    }
}
