package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class encapsulates Duke, an interactive task management chat-bot.
 *
 * @author Kleon Ang
 */
public class Duke extends Application {
    private static final String DATA_FILENAME = "duke.txt";
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    /**
     * Empty constructor for Duke to be launched by Launcher.
     */
    public Duke() {
    }

    /**
     * Gets response from Parser, then exit or rewrite data if needed.
     *
     * @param input User input.
     * @return The response from Duke.
     * @throws DukeException A Duke-specific exception that may occur when parsing user input.
     */
    public static String getResponse(String input) throws DukeException {
        String response = parser.parse(input);
        if (parser.needsToExit()) {
            return response;
        }
        if (parser.needsToRewrite()) {
            storage.rewriteData(tasks);
        }
        return response;
    }

    /**
     * Starts up the user interface with a given Stage.
     * @param stage A Stage for the user interface.
     */
    @Override
    public void start(Stage stage) {
        this.ui = new Ui(stage);
        storage = new Storage(DATA_FILENAME);
        try {
            tasks = new TaskList(storage.load());
            this.ui.showLoadingSuccess(DATA_FILENAME);
        } catch (DukeException e) {
            tasks = new TaskList();
            this.ui.showLoadingError(DATA_FILENAME);
        }
        parser = new Parser(tasks);
    }
}
