package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Executes different actions depending on the command type derived from user input.
 *
 * @author felix-ong
 */
public abstract class Command {
    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param storage Handles the saving and loading of tasks.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true if the command calls for the program to exit,
     * false otherwise.
     *
     * @return true if command calls for the program to exit, false otherwise.
     */
    public abstract boolean isExit();
}
