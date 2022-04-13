package duke;

import command.Command;

/**
 * Represents a personal assistant chatbot that responds to command line inputs.
 *
 * @author felix-ong
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke with default filepath.
     */
    public Duke() {
        this.setStorage("data/tasks.txt");
    }

    /**
     * Constructor for Duke.
     *
     * @param filePath Relative path to the storage location.
     */
    public Duke(String filePath) {
        this.setStorage(filePath);
    }

    /**
     * Sets up the data storage location directory and file.
     *
     * @param filePath Relative path to the storage location.
     */
    private void setStorage(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a string containing the response from Duke to the given command in the input.
     *
     * @return String response from Duke.
     */
    public String getResponse(String input) {
        try {
            assert storage != null : "Storage is uninitialized";
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            return c.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

