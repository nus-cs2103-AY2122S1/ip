package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code ExitCommand}. User input for a
 * {@code ExitCommand} starts with "bye".
 *
 * @author Elizabeth Chow
 */
public class ExitCommand extends Command {
    /**
     * Constructor for a {@code ExitCommand}.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Writes to storage and ui displays an exit message.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToFile(tasks);
        return ui.showExit();
    }

    /**
     * Returns {@code true}. Program should terminate after this {@code ExitCommand}.
     *
     * @return {@code true}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }
}
