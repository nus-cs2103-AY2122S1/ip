package duke;



/**
 * Duke is a simple bot that allows users to keep track of different types of tasks.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

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

    /**
     * Returns parser response from user input.
     *
     * @param input user input
     * @return parser response to user input
     */
    public String getResponse(String input) {
        String response = this.parser.getResponse(input);
        storage.saveData(tasks.encodeTasks());
        return response;
    }


}
