package duke;

import duke.task.DukeList;

/**
 * Encapsulation of Duke's main class.
 */
public class Duke {

    /** Duke's data storage. */
    private final Storage storage;

    /** Duke's Ui. */
    private final Ui ui;

    private final Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        DukeList list = new DukeList();
        storage = new Storage(System.getProperty("user.dir"), list);
        parser = new Parser(list, storage);
        ui = new Ui(parser);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.run();
    }

    /**
     * Returns the response from Duke based on the user input.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        String trimmedInput = input.trim();
        return parser.parse(trimmedInput);
    }

    /**
     * Runs Duke.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
