package duke;

public class Duke {
    private static final String FILE_PATH = "duke.txt";
    private Parser parser;
    private TaskList tasks;

    /**
     * Returns a Duke object. IOException is thrown if file does not
     * exist and is unable to be created.
     *
     */
    public Duke() {
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage(FILE_PATH));
    }

    public String getResponse(String input) {
        return parser.execute(input, tasks);
    }
}
