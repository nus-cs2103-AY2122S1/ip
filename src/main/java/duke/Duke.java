package duke;

import duke.tasks.TaskList;

/**
 * CLI Task manager based on Duke.
 *
 * @author bhcs
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Instantiates the Duke object and its required components.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = storage.readFromDatabase();
        this.parser = new Parser(this.tasks);
    }

    public String getResponse(String input) {
        String output = parser.takeInput(input);
        storage.writeToDatabase(tasks);
        return output;
    }
}
