package duke.core;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Encapsulates the Main Program where Duke is run.
 *
 * @author Clifford
 */
public class Duke {
    private static final String TASKS_FILE_PATH = "bin/data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises Duke for user to interact with.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(TASKS_FILE_PATH);
            taskList = new TaskList(storage);
            taskList.retrieveTasks();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a String representation of the greeting message when the user first starts up Duke.
     *
     * @return the greeting message when user starts up Duke
     */
    public String greetUser() {
        return ui.greetUser();
    }

    /**
     * Generates a response based on the user input.
     *
     * @param userInput the dialogue that the user entered in the text box.
     * @return a response matching the user request.
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.identifyCommand(userInput);
            assert(c != null);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
