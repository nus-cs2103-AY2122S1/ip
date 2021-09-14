import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class to initialize the storage, the task list, and the ui.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for a Duke Object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * A method to get the chat box's response from a user input.
     *
     * @param input user input to be fed into the chat box
     * @return a String that is the chat box's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.getResponse(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
    }
}

