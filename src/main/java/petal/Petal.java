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

    /** Represents whether the user has said bye or not */
    private boolean isBye = false;

    //Components of the Petal instance
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor for the Petal class
     */
    public Petal() {
        ui = new Ui(this);
        taskList = new TaskList(ui);
        storage = new Storage(taskList, ui);
        parser = new Parser();
    }

    /**
     * Flips the boolean isBye and terminates the process
     */
    public void stop() {
        this.isBye = true;
    }

    /**
     * Starts the Petal instance
     */
    public void run() {
        storage.createDirectory();
        while (!isBye) {
            String message = ui.readCommand();
            Command command = parser.handleInput(message);
            command.execute(taskList, ui, storage);
        }
    }

    /**
     * This is the main method for the Petal bot
     * @param args
     */
    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

