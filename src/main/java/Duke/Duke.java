package duke;



/**
 * Duke is a simple bot that allows users to keep track of different types of tasks.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private Parser parser;

    /**
     * Constructs an instance of Duke.
     *
     * @param filePath the path to save data
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = this.storage.loadTasks();
        this.parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        return this.parser.getResponse(input);
    }


}
