package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.TaskList;

/**
 * Abstract class that encapsulates a user command.
 */
public abstract class Command {
    /**
     * An abstract method that will execute a user command and returns
     * a task list containing results from that command.
     *
     * @param taskList The task list for the command to execute on.
     * @return The resulting task list after the command is executed.
     * @throws DukeException If there are any errors with the user input.
     */
    public abstract TaskList execute(TaskList taskList) throws DukeException;
}
