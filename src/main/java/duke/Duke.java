package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents the main Duke application.
 */
public class Duke {
    /**
     * The storage for the application
     */
    private Storage storage;
    /**
     * The task list for the application
     */
    private TaskList taskList;
    /**
     * The UI for the application
     */
    private Ui ui;

    /**
     * Constructs a Duke class with the given data file.
     *
     * @param filePath The path of the data file for the application
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Starts the Duke application with the UI. Writes to the data file after being closed by the user.
     */
    public void run() {
        ui.greet();
        while (ui.isOpen()) {
            try {
                Command command = ui.receiveCommand();
                command.execute(taskList, storage, ui);
            } catch (DukeException e) {
                ui.displayErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
