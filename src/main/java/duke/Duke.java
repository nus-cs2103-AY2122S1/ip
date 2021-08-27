package duke;

import java.io.File;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(new File(filePath));
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command c = parser.parseCommand(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
