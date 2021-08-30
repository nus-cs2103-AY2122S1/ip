package petal;

import petal.command.Command;
import petal.components.Parser;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 *
 * @author Siddanth
 */
public class Petal {

    //Components of the Petal instance
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor for the Petal class
     */
    public Petal() {
        ui = new Ui();
        taskList = new TaskList(ui);
        storage = new Storage(taskList, ui);
        parser = new Parser();
    }

    /**
     * Returns either start message or welcome back message
     * @return String greeting the user
     */
    public String greetUser() {
        //It calls createDirectory as it has to check if user has used Petal before
        return storage.createDirectory();
    }

    /**
     * Starts the Petal instance
     */
    public void run(String message) {
        Command command = parser.handleInput(message);
        command.execute(taskList, ui, storage);
    }
}

