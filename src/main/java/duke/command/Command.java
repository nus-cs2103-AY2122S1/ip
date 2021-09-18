package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command provided by the user.
 */
public interface Command {
    /**
     * Performs the command on the user's task list and returns a display message.
     *
     * @param tasks List of user's tasks.
     * @param ui Ui object to handle display of message to user.
     * @throws MalformedCommandException If command could not be executed successfully.
     */
    String execute(TaskList tasks, Ui ui) throws MalformedCommandException;
}

