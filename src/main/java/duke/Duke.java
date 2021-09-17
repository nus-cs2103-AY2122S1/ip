package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
     *
     * @param pathName The path name to be converted to create a new File instance.
     */
    public Duke(String pathName) {
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
     * Constructor to initialise the Duke chatbot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(PATH_NAME);
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

    /**
     * Calls the welcome method from the ui.
     *
     * @return The standard welcome message.
     */
    public String greetWelcome() {
        return ui.greetWelcome();
    }

    /**
     * Runs and carries out the appropriate action based on the input.
     *
     * @param input The input from the user.
     * @return The message in accordance to the message from the input.
     */
    public String getResponse(String input) {
        ui.greetWelcome();
        try {
            Command c = new Parser().parse(input);
            String output = c.execute(storage, taskList, ui);
            return output;
        } catch (DukeException | IOException e) {
            String output = ui.showError(e.getMessage());
            return output;
        }
    }

    /**
     * Creates the initialization of Duke and runs the programme.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Creates an initialisation of Duke and runs it
        Duke duke = new Duke(PATH_NAME);
        duke.run();

    }
}
