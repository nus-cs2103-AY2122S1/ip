package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates the command interface.
 */
public interface Command {

    /**
     * Returns string response when user enters a command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for a command.
     * @throws DukeException If there are errors executing the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
