package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-Release. Release Duke
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted text.
 * Program stores whatever text entered by the user and display them back
 * to the user when requested.
 *
 * @author Keith Tan
 */
public class Duke {

    private Tasklist taskList;
    private Store storage;
    private Ui ui;

    /**
     * Constructor for Duke.
     * Takes in the file path to load the save data from store
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Store(filePath);
        this.taskList = storage.load();
    }

    /**
     * Gets the response from Duke upon receiving user's input command.
     *
     * @param input String containing the user's input command.
     * @return String containing Duke's response to the user's input command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommandString(input, this.taskList, this.storage);
            return command.execute();
        } catch (DukeException e) {
            return e.toString();
        }

    }

}
