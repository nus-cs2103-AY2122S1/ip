package duke.command;

import duke.task.TaskList;

/**
 * Represents a command by the user.
 */
public abstract class DukeCommand {
    /**
     * Executes the command.
     *
     * @param tl Task list for the user.
     * @return A string to be displayed by the GUI
     */
    public abstract String execute(TaskList tl);

    /**
     * Checks if it is the Bye command.
     *
     * @return True if is Bye, false otherwise.
     */
    public abstract boolean isExit();
}
