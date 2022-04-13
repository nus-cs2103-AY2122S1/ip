import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a chat bot request. A <code>Duke</code> object corresponds to
 * an instance of a chat bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs Duke Object.
     *
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves chat bot's response based on user's command
     *
     * @param fullCommand user input.
     * @return Duke's chat bot message.
     */
    protected String getResponse(String fullCommand) {
        String output;
        try {
            Command c = Parser.parse(fullCommand);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        assert !output.equals("") : "Command response should not be empty";
        return output;
    }
}
