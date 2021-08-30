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
        this.storage = new Storage(System.getProperty("user.dir"), list);
        this.ui = new Ui(list, this.storage);
        this.parser = new Parser(list, this.storage);
        this.storage.load();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.storage.load();
        this.ui.run();
    }

    /**
     * Returns the response from Duke based on the user input.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        return this.parser.parse(input);
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
