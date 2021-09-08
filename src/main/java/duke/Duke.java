package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke class encapsulates a Duke program. It contains the
 * initialization of the Duke bot, as well as the methods
 * required to parse and return user input.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructor for a Duke bot. It also initializes the
     * UI, Storage and tries to load an existing Task List.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("/data/duke.txt");

        try {
            /* try loading text file from storage */
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Takes in a user input, parses it and returns a response
     *
     * @param input user input in the form of a command
     * @return the response with regard to the input
     */
    public String getResponse(String input) {
        assert input != null : "input cannot be null";

        String response = "";

        try {
            response = new Parser(input, this.taskList, this.storage, this.ui).compute();
        } catch (DukeException e) {
            response = e.toString();
        }

        return response;
    }

    /**
     * Returns a welcome message to the user
     *
     * @return Duke's welcome message
     */
    public String welcomeMessage() {
        return this.ui.welcomeMessage();
    }
}
