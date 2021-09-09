package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

/**
 * The main body of Duke task tracking software.
 */
public class Duke {
    private static final String DEFAULT_PATH = "./data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }


    /**
     * Starts the conversation in command line interface.
     * it starts the conversation loop and continuously reads input from
     * user and execute corresponding functions until exit instructions are given.
     */
    public void run() {
        ui.showWelcome();
        boolean hasExited = false;
        while (!hasExited) {
            // conversation loop
            try {
                String input = ui.readLine();
                ui.printHorizLine();
                Command cmd = Parser.parseCommand(input);
                String executionMsg = cmd.execute(tasks, ui, storage);
                ui.printMsg(executionMsg);
                hasExited = cmd.getExecutionResult();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizLine();
            }
        }
    }


    /**
     * Constructor of Duke.
     * It instantiates an empty which save and load its contents
     * to the default file (./data/duke.txt).
     */
    public Duke() {
        this(new Ui(), new Storage(DEFAULT_PATH), new TaskList());
    }


    private Duke(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = taskList;
    }


    /**
     * Constructor of Duke.
     * It instantiates a Duke with initial contents from
     * the file given by the filePath,
     * and associates the Duke's save and load function to that file.
     *
     * @param filePath Path of the file containing initial content of Duke.
     */

    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList();
            tasks.loadFromFile(storage);
        } catch (FileNotFoundException | DukeException e) {
            ui.showError(e.getMessage());
        }
    }


    /**
     * Get response messages from Duke according input.
     *
     * @param input The input string to be parsed.
     * @return Execution message of the input or error messages.
     * @throws DukeException when something goes wrong.
     */

    public String getResponse(String input) throws DukeException {
        Command cmd = Parser.parseCommand(input);

        return cmd.execute(tasks, ui, storage);
    }
}
