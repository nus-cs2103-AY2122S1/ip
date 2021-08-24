package duke.command;

import duke.task.TaskList;

/**
 * Represents a command by the user.
 */
abstract public class DukeCommand {
    /**
     * Executes the command.
     *
     * @param tl Task list for the user.
     */
    abstract public void execute(TaskList tl);

    /**
     * Checks if it is the Bye command
     *
     * @return True if is Bye, false otherwise.
     */
    abstract public boolean isExit();
}
