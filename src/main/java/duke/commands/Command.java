package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Sets the TaskList and Storage for Command to operate on.
     *
     * @param tasks User's Tasklist.
     * @param storage User's Storage.
     */
    public void setData(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Executes the command.
     *
     * Should be overridden by child classes.
     *
     * @return CommandResult of command.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


}
