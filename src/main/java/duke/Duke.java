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
        this.storage = new Storage("data/tasks.txt");
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }
    /**
     * Constructor for Duke.
     *
     * @param filePath Relative path to the storage location.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a string containing the response from Dude to the given command in the input.
     *
     * @return String response from Dude.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            return c.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

