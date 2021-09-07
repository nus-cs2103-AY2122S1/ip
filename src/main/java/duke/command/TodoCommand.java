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
     * @param title Title of the new task.
     */
    public TodoCommand(String title) {
        super(title);
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
        assert args.length == 1 : "Todo Command should only store 1 argument";
        tasks.addTodoTask(args[0]);
        storage.writeToFile(tasks);
        return ui.showNewTask(tasks.lastTask(), tasks.size());
    }
}
