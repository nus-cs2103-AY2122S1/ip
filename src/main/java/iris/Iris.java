package iris;

import iris.command.Command;
import iris.exception.IrisException;
import iris.parser.Parser;
import iris.storage.Storage;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Program to keep track of a list of tasks.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Iris {

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Iris.
     */
    public Iris() {
        taskList = new TaskList();
        storage = new Storage();
        taskList.loadFromStorage(storage.load());
        ui = new Ui();
        parser = new Parser(taskList, storage, ui);
    }

    /**
     * Response of Iris in string form.
     *
     * @param input The user's input
     * @return A string of the response of Iris
     */
    protected String getResponse(String input) {
        try {
            if (!input.isBlank()) {
                Command command = parser.parseUserInput(input);
                return command.runCommand();
            }
        } catch (IrisException e) {
            return e.getMessage();
        }
        return ui.guiHelpMessage();
    }
}
