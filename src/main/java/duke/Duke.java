package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private final Parser parser;
    private final Ui ui;
    private boolean isFirstTimeUser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage();
            taskList = new TaskList(storage.retrieveData(), storage);
            isFirstTimeUser = storage.getUserStatus();
        } catch (DukeException e) {
            ui.formatPrint(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>(), storage);
        }
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public boolean isFirstTimeUser() {
        return this.isFirstTimeUser;
    }
}
