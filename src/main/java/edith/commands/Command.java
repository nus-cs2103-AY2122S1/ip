package edith.commands;

import edith.storage.Storage;
import edith.tasks.TaskList;
import edith.ui.Ui;

import java.io.IOException;

/**
 * Represents user input
 */
public abstract class Command {
    /**
     * Creates tasks according to user input.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @throws IOException If is no user input
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Represents the end of program
     * @return true if program should exit, otherwise do not exit program
     */
    public abstract boolean isExit();

}
