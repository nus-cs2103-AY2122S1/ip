package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code TodoCommand}. User input for a
 * {@code TodoCommand} starts with "todo".
 *
 * @author Elizabeth Chow
 */
public class TodoCommand extends Command {
    /**
     * Constructor for a {@code DeleteCommand}
     *
     * @param args {@code String} array with length 1. {@code args[0]} contains the
     *             task title.
     */
    public TodoCommand(String[] args) {
        super(args);
    }

    /**
     * Adds a {@code Todo} to tasks and writes to storage. Displays the new task in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodoTask(args[0]);
        storage.writeToFile(tasks);
        return ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    /**
     * Returns {@code false}. Program should not terminate after {@code ListCommand}.
     *
     * @return {@code false}
     */
    public boolean isExit() {
        return false;
    }
}
