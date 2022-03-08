package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private final Storage storage;
    private final TaskHandler taskHandler;
    private final Ui ui;
    private final Parser parser;

    /**
     * Instantiates Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskHandler = new TaskHandler(storage.loadTasks());
        parser = new Parser();
    }

    /**
     * Gets the appropriate response from Duke to respond to user.
     *
     * @param input Raw user input.
     * @return String representation of Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parseRawInput(input);
            return c.execute(taskHandler, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
