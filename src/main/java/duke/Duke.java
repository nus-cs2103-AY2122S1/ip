package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a program called Duke. A <code> Duke </code> is a software which
 * acts as a todolist.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for Duke. Initialises Ui, Storage and TaskList objects.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.initialise());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, ui, taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main method for Duke program.
     *
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
