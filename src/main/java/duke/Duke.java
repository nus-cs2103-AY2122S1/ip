package duke;

import javafx.application.Platform;

/**
 * The main controller for the entire Duke program.
 */
public class Duke {
    /** Encapsulates the storage of tasks in a file within the user's computer for continuity across Duke sessions. */
    private final Storage STORAGE;
    /**
     * Stores the tasks when the Duke program runs.
     * Allows one to perform actions on those tasks like add and delete.
     */
    private TaskList tasks;
    /** Handles the parsing of the commands the user inputs into the Duke program. */
    private final Parser PARSER;

    /**
     * Constructs an instance of the Duke program.
     * If there is no file at the file path, a new file will be created;
     * else, the tasks in the file will be read and saved.
     */
    public Duke() {
        final String FILE_PATH = "dukedata.txt";
        STORAGE = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
        PARSER = new Parser();
    }

    /**
     * Gets the appropriate program response based on user input.
     *
     * @return A string to output to the user.
     */
    public String getResponse(String input) {
        DukeStatus currentStatus = PARSER.parse(input, tasks);

        try {
            STORAGE.rewriteData(tasks.convertToSaveFormat());
        } catch (DukeException e) {
            currentStatus.setResponse(Ui.getResponse(e.getMessage()));
        }

        if (currentStatus == DukeStatus.INACTIVE) {
            // TODO: 1 second delay
            Platform.exit();
            System.exit(0);
        }
        return currentStatus.getResponse();
    }
}
