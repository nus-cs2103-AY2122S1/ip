package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command given by the user.
 */
public abstract class Command {

    /**
     * Executes the action for this command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /** Returns true if the command is an exit command */
    /**
     * Returns true if the command causes program to exit.
     *
     * @return true if command is exit command.
     */
    public abstract boolean isExit();
}