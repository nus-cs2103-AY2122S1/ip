package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the main class that the program runs from.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the program
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/data/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }


    public String getResponse(String input) {
        Command c = Parser.parse(input);

        try {
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printDukeException(e.getMessage());
        }
    }

    public Ui getUi() {
        return this.ui;
    }
}
