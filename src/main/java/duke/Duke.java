package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke
     * @param filePath path to storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.setNumTaskDone(storage.getNumTaskDone());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            tasks.setNumTaskDone(0);
        }
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Sorry, Something is wrong!"
                    + "The handling of this error is not required by minimum iP requirements";
        }
    }
}
