package duke;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;


/**
 * Duke application, which is a Personal Assistant Chat bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Returns a new Duke object.
     * @param filePath The specified path for the Duke save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(Storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String command) {
        String response;
        try {
            response = Parser.parse(command).execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
