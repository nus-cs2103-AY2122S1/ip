package duke;

import java.io.IOException;

/**
 * This class encapsulates the Duke application.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor loads the save file from the specified filepath.
     *
     * @param filepath The path to the save file (if any).
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to the input string.
     *
     * @param input The input into Duke.
     * @return Duke's response.
     */
    public String handleInput(String input) {
        String response;
        try {
            response = Parser.parse(input).execute(tasks);
            storage.save(tasks.toSaveFormat());
        } catch (DukeException e) {
            response = e.getMessage() + "\n";
        }
        return "Duke's response:\n" + response;
    }
}
