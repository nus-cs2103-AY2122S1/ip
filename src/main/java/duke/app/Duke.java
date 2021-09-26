package duke.app;

import duke.commands.Command;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import duke.utils.DukeException;
import duke.utils.Parser;

/**
 * The main program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor. Initializes the ui, database and tasklists
     *
     * @param filePath  takes in filepath to for the database(.txt)
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


