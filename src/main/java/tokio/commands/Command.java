package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Represents user input.
 */
public abstract class Command {
    /**
     * Creates tasks according to user input.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @throws IOException If task cannot be written to the storage file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Indicates the end of program.
     *
     * @return true if program should exit, otherwise do not exit program.
     */
    public abstract boolean isExit();

}
