package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code EventCommand}. User input for a
 * {@code EventCommand} starts with "event".
 *
 * @author Elizabeth Chow
 */
public class EventCommand extends Command {
    /**
     * Constructor for a {@code EventCommand}
     *
     * @param args {@code String} array with length 2. {@code args[0]} contains the
     *             title, {@code args[1]} contains the deadline for a {@code Event}.
     */
    public EventCommand(String[] args) {
        super(args);
    }

    /**
     * Adds a {@code Event} to tasks and writes to storage. Displays the new task in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEventTask(args[0], args[1]);
        storage.writeToFile(tasks);
        return ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    /**
     * Returns {@code false}. Program should not terminate after
     * {@code EventCommand}.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
