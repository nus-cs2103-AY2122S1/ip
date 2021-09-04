package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Command class encapsulates a command to be executed by Duke.
 * Each Command has a unique command word associated with it.
 */
public abstract class Command {

    /**
     * Executes the Command based on a given TaskList and Storage.
     *
     * @param tasks the given TaskList.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when the Command cannot be executed.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true if the Command is the ExitCommand, false otherwise.
     * @return true if the Command is the ExitCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
