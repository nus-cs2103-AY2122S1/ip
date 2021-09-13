package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents commands that to be executed by the program based on the input from the user.
 */

public abstract class Command {

    /**
     * Returns true if the command is an exit command.
     * @return a boolean that is true if the command is an exit command.
     */
    public abstract boolean isExit();

    /**
     * Executes the command based on the type of command it is.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException The exception that is thrown when an error is occurred.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
