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
     */
    public abstract void execute(TaskList tl);

    /**
     * Checks if it is the Bye command.
     *
     * @return True if is Bye, false otherwise.
     */
    public abstract boolean isExit();
}
