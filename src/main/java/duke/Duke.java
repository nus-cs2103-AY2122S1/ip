package duke;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * The entry point to the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Default constructor for GUI Launcher.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
    }

    public void start() {
        try {
            taskList = storage.load();
            ui.greetWithFamiliarity(taskList);
        } catch (DukeException e) {
            ui.showDukeException(e);
            storage.resetTasks();

            if (taskList != null) {
                taskList.clearTasks();
            }
        } finally {
            parser = new Parser(storage, ui, taskList);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
