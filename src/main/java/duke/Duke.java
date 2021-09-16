package duke;

import java.io.IOException;

import duke.command.Command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * A Personal Assistant Chat bot that helps to keep track of tasks
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Duke object
     *
     * @param filepath file location
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            taskList = storage.loadData();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(this.taskList);
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);

        } catch (DukeException e) {
            return e.getError();
        }
    }
}
