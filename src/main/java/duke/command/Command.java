package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a general Command to be executed.
 */
public abstract class Command {

    /**
     * Executes the necessary command using the given TaskList, Ui and Storage.
     *
     * @param tasks The given TaskList to be updated.
     * @param ui The given Ui to print messages to the user.
     * @param storage The given Storage to save changes to.
     * @throws DukeException When a command is unable to be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the program is to be terminated or not.
     *
     * @return True if it is the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
