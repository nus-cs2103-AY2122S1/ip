package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Duke(Hiko created by me) is a personal assistant.
 *
 * @author Chen Yanyu
 */
public class Duke {
    private static final String PATH = "data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private final List<String> history = new ArrayList<>();
    private int historyIndex = 0;

    /**
     * Public constructor for Duke.
     *
     * @param path the path for the saved file.
     */
    public Duke(String path) {
        this.storage = new Storage(path);
    }

    /**
     * Gets the response according to the user input.
     *
     * @param input the raw input as a string
     * @return a string of response
     */
    public String getResponse(String input) {
        try {
            history.add(input);
            historyIndex = history.size();
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads the locally saved tasks.
     *
     * @return the string for loading status.
     */
    public String getSavedTasks() {
        try {
            this.tasks = new TaskList(storage.load());
            return "Saved tasks loaded successfully!";
        } catch (DukeException e) {
            this.tasks = new TaskList();
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message.
     *
     * @return a string of welcome message.
     */
    public String getWelcome() {
        String logo =
                " _   _ _ _\n"
                        + "| | | (_) | _____\n"
                        + "| |_| | | |/ / _ \\\n"
                        + "|  _  | |   < (_) |\n"
                        + "|_| |_|_|_|\\_\\___/\n";

        return "Hello from\n" + logo + "What can I do for you?\n";
    }

    /**
     * Gets the last user input in the history of this instance.
     * return the first input if there is no more last history.
     *
     * @return a string of user input history
     */
    public String getLastInput() {
        historyIndex = Math.max(historyIndex - 1, 0);
        return history.get(historyIndex);
    }

    /**
     * Gets the next user input in the history of this instance.
     * return an empty string if there is no more next history.
     *
     * @return a string of user input history
     */
    public String getNextInput() {
        try {
            String nextInput = history.get(historyIndex + 1);
            historyIndex = Math.min(historyIndex + 1, history.size() - 1);
            return nextInput;
        } catch (IndexOutOfBoundsException e) {
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
