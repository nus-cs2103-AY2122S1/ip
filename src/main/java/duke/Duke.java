package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.response.DukeBadResponse;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.util.DukeProperties;
import javafx.application.Application;

/**
 * Represents the main Duke application.
 */
public class Duke {
    public static final String DEFAULT_STORAGE_FILE_PATH = "./data/tasks.txt";

    private final Storage storage;
    private TaskManager taskManager;
    private final Ui ui;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        ui = new Ui();
        String filePath = DukeProperties.getPropertyOrDefault("storage", DEFAULT_STORAGE_FILE_PATH);
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
    public DukeResponse greet() {
        if (taskManager.getTaskCount() > 0) {
            return new DukeResponse("Welcome back! I'm Duke. " + storage.getTasksLoadedMessage(taskManager));
        } else {
            return new DukeResponse("Hello! I'm Duke. What can I do for you?");
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        DukeResponse startUpResponse = greet();
        ui.print(startUpResponse.toString());
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
     *
     * @param input User input.
     * @return Corresponding <code>DukeResponse</code>. If a <code>DukeException</code> was thrown, it is wrapped
     * in a <code>DukeBadResponse</code>.
     */
    public DukeResponse getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            DukeResponse response = command.execute(taskManager, storage);
            return response;
        } catch (DukeException e) {
            return new DukeBadResponse(e.getMessage());
        }
    }

    /**
     * Main entrypoint for the Duke application.
     *
     * @param args Arguments that allow user to start the CLI version.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            new Duke().run();
        } else {
            Application.launch(Gui.class, args);
        }
    }
}
