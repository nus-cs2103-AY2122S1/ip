package duke;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

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
        parser = new Parser(this.taskList, this.ui, this.storage);
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getResponse(String input) {
        try{
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getError();
        }
    }
}
