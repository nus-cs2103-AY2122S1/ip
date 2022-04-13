package jared;

import jared.common.DukeException;
import jared.storage.Storage;
import jared.task.TaskList;
import jared.ui.Ui;

/**
 * Class for the Jared project. Allows user to create a task list to keep track of Todos, Deadlines and Events.
 */
public class Jared {
    private static final String FILE_NAME = "data.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Jared Class.
     */
    public Jared() {
        ui = new Ui();
        storage = new Storage(FILE_NAME);
        try {
            tasks = new TaskList(storage.loadData(), storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the Jared instance. Starts up the scanner to receive tasks.
     */
    public void run() {
        try {
            storage.loadData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        return "Jared heard:\n" + ui.runLogic(input, tasks);
    }

    /**
     * Main function.
     */
    public static void main(String[] args) {
        new Jared().run();
    }
}

