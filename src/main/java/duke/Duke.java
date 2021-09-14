package duke;

import duke.commands.Command;
import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Main class of the program
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    /**
     * Initialises all class needed for duke.
     * Trigger storage to open existing data or create a new file
     * for the data.
     */
    public void run() {
        storage = new Storage();
        ui = new Ui();
        parser = new Parser(storage);
        storage.open();
    }
    public String greet() {
        return ui.greet(true);
    }

    /**
     * Get the appropriate response for an input from the user.
     *
     * @param input The User input.
     * @return The Duke output.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(ui, storage);
        } catch (DukeExceptions e) {
            return ui.printException(e);
        }
    }
}
