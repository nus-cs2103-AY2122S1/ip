package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command in the Duke program.
 */
public abstract class Command {
    /**
     * Returns the response of this command.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     * @throws DukeException If there is/are invalid argument(s) passed into this command,
     *                       or changes cannot be saved to storage.
     */
    public abstract String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if this command is not the ExitCommand.
     *
     * @return true if this command is not the ExitCommand.
     */
    public abstract boolean isExit();
}
