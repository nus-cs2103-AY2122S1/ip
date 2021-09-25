package iris.command;

import iris.exception.IrisException;
import iris.storage.Storage;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Commands that can be executed by Iris.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public abstract class Command {

    protected final TaskList taskList;
    protected final Storage storage;
    protected final Ui ui;

    /**
     * Constructor for a Command.
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
     * @return String representation of Iris's response.
     * @throws IrisException when an error occurs.
     */
    public abstract String runCommand() throws IrisException;
}
