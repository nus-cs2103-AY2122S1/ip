package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Initializes an instance of Duke.
     *
     * @param filePath The storage location for Duke's tasklist.
     * @throws Exception
     */
    public Duke(String filePath) throws DukeException {
        super();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks());
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes the given command.
     *
     * @param fullCommand The full string of the command to execute.
     * @return A boolean representing whether this instance of Duke should exit.
     */
    public boolean executeCommand(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            return command.shouldExit();
        } catch (DukeException e) {
            ui.printMessage("Error: " + e.getMessage());
            return false;
        }
    }
}
