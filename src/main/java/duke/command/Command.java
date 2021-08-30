package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Commands that can be executed by Duke.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public abstract class Command {
    protected final TaskList taskList;
    protected final Storage storage;
    protected final Ui ui;

    /**
     * Constructor for a DukeCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     */
    public Command(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command.
     *
     * @throws DukeException when an error occurs.
     */
    public abstract void runCommand() throws DukeException;
}
