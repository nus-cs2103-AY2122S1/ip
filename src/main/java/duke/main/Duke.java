package duke.main;

import duke.exception.DukeException;

import duke.task.TaskList;

import duke.util.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates duke program
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for duke
     *
     * @param filePath path in which data is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns response by duke after user input.
     *
     * @param input
     * @return String representation of duke response to user
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public void save() throws DukeException {
        storage.save(taskList, "update");
    }

}