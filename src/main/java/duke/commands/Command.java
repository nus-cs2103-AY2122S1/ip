package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * The abstract class representing a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The list of tasks that the command should be executed on.
     * @return The response to be displayed in the GUI.
     * @throws DukeException If the action is unable to be completed.
     */
    public abstract String execute(TaskList taskList) throws DukeException;
}
