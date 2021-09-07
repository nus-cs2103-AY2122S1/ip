package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Duke class is the main class which starts the chatbot
 */
public class Duke {

    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final String FILE_PATH = "./data/duke.txt";
    private final String FOLDER_PATH = "./data";

    /**
     * Initialises the ui, storage and tasklist before the chatbot runs
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, FOLDER_PATH);
        taskList = new TaskList();
        try {
            storage.readTasks(taskList);
        } catch (DukeException ex) {
            ui.displayLoadingError(ex);
            taskList = new TaskList();
        }
    }

    /**
     * Executes the commands parsed by the input
     *
     * @param input user's input text
     * @param ui Duke's ui
     * @return a string representation of the chatbot's output message
     */
    public String getResponse(String input, Ui ui) {
        try {
            storage.saveTasks(taskList);
            Command c = Parser.parse(input, taskList);
            assert !c.execute(taskList, ui).isEmpty();
            return c.execute(taskList, ui);
        } catch (DukeException ex) {
            return ui.displayError(ex.getMessage());
        }
    }
}
