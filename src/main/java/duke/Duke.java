package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Creates and runs a Duke bot. Main file for the bot that is run to create a new Duke bot.
 */
public class Duke {
    private static final String FILE_PATH = System.getProperty("user.dir");
    private Storage storage;
    private Ui ui;

    /**
     * Returns a Duke object with storage set to a specified filepath.
     *
     * @param filepath The filepath for the creation of the storage system.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
    }

    public Duke() {
        this(FILE_PATH);
    }

    /**
     * Returns a welcome message in String representation.
     */
    public String greet() {
        return this.ui.welcomeMessage();
    }

    /**
     * Returns a welcome message in String representation.
     *
     * @param input The input entered by the user.
     * @return The response to be output by the bot in String representation.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parseInput(input);
            response = c.execute(storage, ui);
        } catch (DukeException e) {
            return Ui.showErrorMessage(e.getMessage());
        }
        return response;
    }

}
