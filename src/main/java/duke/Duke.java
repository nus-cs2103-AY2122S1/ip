package duke;

/**
 * Main class for Duke, scans for inputs and responds to user.
 */
public class Duke {
    private final String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke program.
     *
     * @param filePath Relative path of cache as string.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initialize the Duke program
     *
     * @return A string describing the response.
     */
    public String initialize() {
        try {
            storage = new Storage(filePath);
            tasks = storage.load();
            ui = new Ui(tasks, storage);
            return ui.initialize();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes an input from the user.
     *
     * @param message String received from the user.
     * @return A response to be displayed to the user.
     */
    public String getResponse(String message) {
        return ui.respond(message);
    }
}
