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
    private final String FILE_PATH = "src/main/java/data/duke.txt";
    private final String FOLDER_PATH = "src/main/java/data";

    /**
     * Public constructor which initialises the ui, storage and tasklist before the chatbot runs
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
     * Starts the reading of the commands and the execution of the instructions
     */

    public String getResponse(String input, Ui ui) {
       try {
           storage.saveTasks(taskList);
           Command c = Parser.parse(input, taskList);
           return c.execute(taskList, ui);
       } catch (DukeException ex) {
           return ui.displayError(ex.getMessage());
       }
    }
}
