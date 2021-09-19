package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Abstract command class.
 */
public abstract class Command {
    /** Indicates when Duke should terminate. */
    protected boolean isExit = false;

    /**
     * Returns whether to exit the chat bot.
     *
     * @return whether to exit the chat bot.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return response for successful execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
