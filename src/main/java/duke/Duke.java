package duke;

/**
 * A driver class that initializes the the parser, task list and storage for the chatterbot.
 */

public class Duke {
    private Parser parser;
    private Storage storage;
    private DukeUi ui;

    /**
     * The constructor for a Duke Object.
     */
    public Duke() {
        ui = new DukeUi();
        storage = new Storage();
        storage.autoLoad();
        parser = new Parser(new TaskList(storage));
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * This method starts and ends the program, and pass the user input to the parser.
     */
    public void run() {
        ui.printWelcomeMessage();
        ui.readUserInput(parser);
        ui.printExitMessage();
    }
}
