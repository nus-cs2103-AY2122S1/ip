package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract command class providing the skeleton of all commands
 */
public abstract class Command {
    //TODO: Add alternative constructor without storage parameter.
    /**
     * Performs the command.
     *
     * @param tasks TaskList to be manipulated if necessary.
     * @param ui Ui to display command message to.
     * @param storage Storage to interact with if necessary.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks whether the command should terminate Duke.
     *
     * @return Whether Duke should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    protected abstract String getMessage(TaskList tasks);
}
