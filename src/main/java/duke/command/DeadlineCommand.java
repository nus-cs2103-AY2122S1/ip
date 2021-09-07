package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code DeadlineCommand}. User input for a
 * {@code DeadlineCommand} starts with "deadline".
 *
 * @author Elizabeth Chow
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for a {@code DeadlineCommand}
     *
     * @param title   Title of the new task.
     * @param endDate End date of the new task.
     */
    public DeadlineCommand(String title, String endDate) {
        super(title, endDate);
    }

    /**
     * Adds {@code Deadline} to tasks and writes to storage. Displays the new task in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert args.length == 2 : "Deadline Command should only store 2 arguments";
        tasks.addDeadlineTask(args[0], args[1]);
        storage.writeToFile(tasks);
        return ui.showNewTask(tasks.lastTask(), tasks.size());
    }
}
