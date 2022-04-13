package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.TaskList;

/**
 * Represents a command.
 * Contains information needed to execute the command.
 */
public abstract class Command {
    protected final String information;

    /**
     * Constructor for Command. (For invocation by subclass constructors).
     *
     * @param information information needed to run the command.
     */
    public Command(String information) {
        this.information = information;
    }

    /**
     * Performs operations on taskList object and storage object.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return Output string to display to user.
     * @throws NyxException If an error is encountered while running the command.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws NyxException;
}
