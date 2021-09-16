import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Project Duke is an educational software project.
 * It stores tasks entered by users locally and reloads then on every start-up.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the instance of Duke.
     * The path to the local file and tasklist will be initialised.
     *
     * @param filePath the path to the local file
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }


    protected String getResponse(String input) {
        try {
            Command c = Command.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
