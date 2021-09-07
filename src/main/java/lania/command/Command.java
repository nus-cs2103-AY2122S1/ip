package lania.command;

import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * Abstract class that represents various types of commands.
 */
public abstract class Command {

    /** Whether the command is an exit command */
    protected boolean isExit = false;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return True if the command is an ExitCommand, false if otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Performs different executions depending on the type of Command.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the corresponding command.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui, Log log);
}
