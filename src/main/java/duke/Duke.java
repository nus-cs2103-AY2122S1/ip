package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Duke bot that can interact with users
 * and keep track of different tasks.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);

    }

    /**
     * A public constructor for Duke.
     */
    public Duke() {
        this("data/tasks.txt");
    }

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

}

