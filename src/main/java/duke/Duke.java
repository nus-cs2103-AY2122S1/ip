package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the main Duke application.
 */
public class Duke {
    private final Storage storage;
    private TaskManager taskManager;
    private final Ui ui;

    /**
     * Constructor for a Duke object.
     * @param filePath the path to the file where tasks should be stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.loadTasks());
        } catch (DukeException e) {
            ui.print(e.getMessage());
            taskManager = new TaskManager();
        }
    }

    /**
     * Returns a <code>DukeResponse</code> on startup.
     * Displays the saved tasks if the task manager was loaded from storage.
     */
    public DukeResponse startUp() {
        if (taskManager.getTaskCount() > 0) {
            return new DukeResponse("Welcome back! I'm Duke. These are the tasks I recall from your last visit.\n\n"
                    + taskManager);
        } else {
            return new DukeResponse("Hello! I'm Duke. What can I do for you?");
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        String startUpMessage = startUp().toString();
        ui.print(startUpMessage);
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            DukeResponse response = getResponse(input);
            ui.print(response.toString());
            isExit = response.isExit();
        }
    }

    /**
     * Returns the appropriate response according to the user input.
     * @param input the user input
     * @return the corresponding <code>DukeResponse</code>. If a <code>DukeException</code> was thrown, it is wrapped
     * in a <code>DukeResponse</code>.
     */
    public DukeResponse getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            DukeResponse response = command.execute(taskManager, storage);
            return response;
        } catch (DukeException e) {
            return new DukeResponse(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
