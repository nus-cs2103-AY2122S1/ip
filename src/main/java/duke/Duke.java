package duke;

/**
 * A driver class that initializes the the parser, task list and storage for the chatterbot.
 * @author Zhao Peiduo
 */

public class Duke {
    private final Parser parser;

    /**
     * The constructor for a Duke Object.
     */
    public Duke() {
        Storage storage = new Storage();
        storage.autoLoad();
        parser = new Parser(new TaskList(storage));
    }

    public String getResponse(String userInput) {
        return parser.parse(userInput);
    }
}
