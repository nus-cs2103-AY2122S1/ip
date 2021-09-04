package duke;

import duke.command.Command;

import duke.data.DukeException;
import duke.data.Parser;
import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;

/**
 * The Duke iP Program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates the appropriate response based on the input of the user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert ui != null : "Ui should have been initialised first!";
            assert storage != null : "Storage should have been initialised first!";
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
