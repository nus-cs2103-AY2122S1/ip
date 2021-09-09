package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Program to keep track of a list of tasks.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private boolean isActive;
    /**
     * Constructor for Duke.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage();
        taskList.loadFromStorage(storage.load());
        ui = new Ui();
        parser = new Parser(taskList, storage, ui);
        isActive = true;
    }

    /**
     * Response of Duke in string form.
     *
     * @param input The user's input
     * @return A string of the response of Duke
     */
    protected String getResponse(String input) {
        if (isActive) {
            try {
                Command command = parser.parseUserInput(input);
                if (command instanceof ExitCommand) {
                    isActive = false;
                }
                return command.runCommand();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
