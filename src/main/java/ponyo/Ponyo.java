package ponyo;

import javafx.application.Application;
import ponyo.commands.Command;
import ponyo.data.exceptions.PonyoException;
import ponyo.data.task.TaskList;
import ponyo.parser.Parser;
import ponyo.storage.Storage;

public class Ponyo {
    // CONSTANTS
    private static final String PATH = "data";
    private static final String FILENAME = "tasks.txt";
    private static final String FILEPATH = PATH + "/" + FILENAME;

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Ponyo program instance.
     */
    public Ponyo() {
        try {
            storage = new Storage(FILEPATH);
            tasks = new TaskList(storage.load());
        } catch (PonyoException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Generates response to user input.
     *
     * @param input Input provided by user.
     * @return Response message by system.
     */
    public String[] handleInput(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (PonyoException e) {
            return new String[]{ e.getMessage() };
        }
    }
}
