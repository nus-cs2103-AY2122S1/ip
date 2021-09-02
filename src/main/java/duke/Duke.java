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

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
    }

    public Duke() {
        this(FILE_PATH);
    }

    public String greet() {
        return this.ui.welcomeMessage();
    }

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

    //    /**
    //     * Starts up the bot for CLI text-based UI.
    //     */
    //    public void run() {
    //        this.ui.welcomeMessage();
    //        boolean isExit = false;
    //        while (!isExit) {
    //            try {
    //                String inputCommand = ui.readCommand();
    //                Command c = Parser.parseInput(inputCommand);
    //                c.execute(storage, ui);
    //                isExit = c.isExit();
    //            } catch (DukeException e) {
    //                Ui.showErrorMessage(e.getMessage());
    //            }
    //        }
    //    }
    //
    //    public static void main(String[] args) {
    //        new Duke(FILE_PATH).run();
    //    }
}
