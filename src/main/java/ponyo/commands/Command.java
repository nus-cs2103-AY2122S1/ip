package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.storage.Storage;

/**
 * Represents an executable command
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     * @param tasks The list of tasks stored.
     * @param storage Load tasks and save tasks to file
     * @return a String array of responses to communicate with user
     */
    public abstract String[] execute(TaskList tasks, Storage storage);

    /**
     * @return true if the command is meant to exit the program,
     *         false otherwise.
     */
    public abstract boolean isExitCommand();
}
