package domain.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import api.input.classes.input_manager.InputManager;
import api.input_entities_api.exceptions.IncorrectInputException;
import api.input_entities_api.movie.MovieBuilderDirector;
import domain.commands.exceptions.EmptyCollectionException;
import entities.classes.Movie;
import entities.enums.MpaaRating;
import repository.exceptions.KeyNotFoundException;
import repository.interfaces.Repository;

public class CollectionManager {
    private Repository repository;
    private Date initializationDate;
    private InputManager inputManager;
    private MovieBuilderDirector movieBuilderDirector;

    public CollectionManager(Repository repository, InputManager inputManager) {
        this.repository = repository;
        this.inputManager = inputManager;
        movieBuilderDirector = new MovieBuilderDirector(inputManager);

        // Чтение или запись инициализационной даты
        File file = new File("config/config_file");
        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String dateStr = reader.readLine();
                initializationDate = new Date(Long.parseLong(dateStr));
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                initializationDate = new Date();
            }
        } else {
            initializationDate = new Date();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(initializationDate.getTime()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void info() throws KeyNotFoundException {
        System.out.println("Тип коллекции: " + repository.toString());
        System.out.println("Тип элементов: " + ((repository.getSize() == 0) ? "не определен" : repository.getByIndex(0).getClass().getSimpleName()));
        System.out.println("Дата инициализации: " + initializationDate);
        System.out.println("Количество элементов: " + repository.getSize());
    }

    public void add() throws KeyNotFoundException, IncorrectInputException {
        repository.post(movieBuilderDirector.inputMovie());
        
        if (inputManager.toString() != "FileInput") {
            System.out.println("Элемент успешно добавлен.");
        }
    }

    public void save() throws KeyNotFoundException, IncorrectInputException {
        repository.writeIntoFile();

        if (inputManager.toString() != "FileInput") {
            System.out.println("Коллекция успешно сохранена.");
        }
    }

    public void show() throws KeyNotFoundException, IncorrectInputException {
        if (repository.getSize() == 0) {
            System.out.println("Коллекция пустая.");
        } else {
            for (int i = 0; i < repository.getSize(); i++) {
                System.out.println(repository.getByIndex(i).toString());
            }
        }
    }

    public void update(int id) throws KeyNotFoundException, IncorrectInputException {
        Movie movie = movieBuilderDirector.inputMovie();
        int nextId = movie.getId();
        movie.setId(id);
        Movie.setNextId(nextId);
        repository.put(id, movie);

        if (inputManager.toString() != "FileInput") {
            System.out.println("Элемент был успешно заменён.");
        }
    }

    public void remove_by_id(int id) throws KeyNotFoundException, IncorrectInputException {
        repository.delete(id);

        if (inputManager.toString() != "FileInput") {
            System.out.println("Элемент был успешно удалён.");
        }
    }

    public void clear() throws KeyNotFoundException, IncorrectInputException {
        while (repository.getSize() > 0) {
            repository.delete(repository.getByIndex(0).getId());
        }

        if (inputManager.toString() != "FileInput") {
            System.out.println("Коллекция была успешно очищена.");
        }
    }

    public void remove_at(int index) throws KeyNotFoundException, IncorrectInputException {
        repository.delete(repository.getByIndex(index).getId());

        if (inputManager.toString() != "FileInput") {
            System.out.println("Элемент был успешно удалён.");
        }
    }

    public void remove_first() throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException {
        if (repository.getSize() == 0) {
            throw new EmptyCollectionException("Ошибка: выполнить действие не удалось, так как коллекция пустая. ");
        }
        repository.delete(repository.getByIndex(0).getId());
        if (inputManager.toString() != "FileInput") {
            System.out.println("Элемент был успешно удалён.");
        }
    }

    public void remove_lower() throws KeyNotFoundException, IncorrectInputException {
        Movie compMovie = movieBuilderDirector.inputMovie();
        int value = repository.getSize();
        ArrayList<Integer> arr = new ArrayList<>(); 

        for (int i = 0; i < repository.getSize(); i++) {
            if (compMovie.compareTo(repository.getByIndex(i)) == 1) {
                arr.add(repository.getByIndex(i).getId());
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            repository.delete(arr.get(i));
        }

        if (inputManager.toString() != "FileInput") {
            System.out.println(value - repository.getSize() + " элементов был успешно удалено.");
        }
    }

    public void max_by_creation_date() throws KeyNotFoundException, EmptyCollectionException {
        if (repository.getSize() == 0) {
            throw new EmptyCollectionException("Ошибка: выполнить действие не удалось, так как коллекция пустая. ");
        }
        Movie maxElement = repository.getByIndex(0);

        for (int i = 1; i < repository.getSize(); i++) {
            int comparison = maxElement.getCreationDate().compareTo(repository.getByIndex(i).getCreationDate());

            if (comparison <= 0) {
                maxElement = repository.getByIndex(i);
            }
        }
        System.out.println(maxElement);
    }

    public void filter_starts_with_name(String arg) throws KeyNotFoundException {
        for (int i = 0; i < repository.getSize(); i++) {
            if (repository.getByIndex(i).getName().startsWith(arg)) {
                System.out.println(repository.getByIndex(i));
            }
        }
    }

    public void print_unique_mpaa_rating() throws KeyNotFoundException {
        Set<MpaaRating> set = new HashSet<>();
        
        for (int i = 0; i < repository.getSize(); i++) {
            set.add(repository.getByIndex(i).getMpaaRating());
        }

        Iterator<MpaaRating> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
