package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The Duke class encapsulates the action of the Duke chatbot.
 */
public class Duke {
    /** The path name to be converted to create a new File instance. */
    private static final String PATH_NAME = "./data/tasklist.txt";

    /** The storage for Duke to save and load the tasklist to. */
    private Storage storage;

    /** The tasklist for Duke to store all the tasks. */
    private TaskList taskList;

    /** The Ui for Duke to deal with all the interactions with the user. */
    private Ui ui;

    /**
     * Constructor to initialise the Duke chatbot.
     */
    private Duke(String pathName) {
        this.ui = new Ui();
        this.storage = new Storage(pathName);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.greetWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = new Parser().parse(fullCommand);
                c.execute(storage, taskList, ui);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // Creates an initialisation of Duke and runs it
        Duke duke = new Duke(PATH_NAME);
        duke.run();

    }
}
