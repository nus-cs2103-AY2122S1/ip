package duke;

import java.io.IOException;

/**
 * A driver class that initializes the the parser, task list and storage for the chatterbot.
 */

public class Duke {
    private Parser parser;
    private Storage storage;

    /**
     * The constructor for a Duke Object.
     */
    public Duke() {
        storage = new Storage();
        storage.autoLoad();
        parser = new Parser(new TaskList(storage));
    }

    public String getResponse(String userInput) {
        return parser.parse(userInput);
    }
}
