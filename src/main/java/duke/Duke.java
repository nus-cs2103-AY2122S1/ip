package duke;

import java.util.*;

import duke.commands.Command;
import duke.utils.*;

/**
 * The main program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor. Initializes the ui, database and tasklists
     *
     * @param filePath  takes in filepath to for the database(.txt)
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to run the program. Will not be terminated unless user exits or gives the bye command
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point for the JAR file.
     *
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data");
        duke.run();
    }
}
