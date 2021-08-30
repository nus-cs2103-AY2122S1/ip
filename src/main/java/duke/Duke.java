package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    protected Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates duke with the file path given.
     *
     * @param filePath The file path where the tasks are stored locally.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                return ui.farewellUser();
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

