package duke.main;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Duke program implements an application that takes in user input and acts as task list manager for users.
 *
 * @author  Nicholas Loo
 * @version 0.1
 * @since   2021-08-25
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor specifying the file path where data is stored.
     *
     * @param filePath The file path giving the location of the file containing user data
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readPastData());
        } catch (FileNotFoundException e) {
            storage.createDataFile();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = this.ui.readCommand();
                Command command = Parser.parse(userCommand);
                command.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }

    }

    /**
     * This is the main method which makes use of the Duke constructor and the run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
