package duke;

import duke.main.DukeException;
import duke.main.Executor;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

import java.io.File;

/**
 * The entry point to the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Executor executor;

    /**
     * Default constructor for GUI Launcher.
     */
    public Duke() {
        String filePath = System.getProperty("user.dir") + File.separator + "tasks.txt";
        storage = new Storage(filePath);
    }

    /**
     * Start the assistant.
     */
    public void start() {
        try {
            taskList = storage.loadTaskList();
            ui.greetWithFamiliarity(taskList);
        } catch (DukeException e) {
            ui.showDukeException(e);

            //ensure that taskList has loaded before clearing tasks
            if (taskList != null) {
                taskList.clearTasks();
            }
        } finally {
            executor = new Executor(storage, ui, taskList);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return executor.parseAndExecute(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Set the ui to the given Ui object.
     *
     * @param ui Ui to be set
     */
    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
