package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * This class represents a {@code DeleteCommand}. User input for a
 * {@code DeleteCommand} starts with "delete".
 *
 * @author Elizabeth Chow
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for a {@code DeleteCommand}
     *
     * @param taskNo String representation of the task
     *               number of the task to be deleted.
     */
    public DeleteCommand(String taskNo) {
        super(taskNo);
    }

    /**
     * Deletes a task with the given task number. Writes to storage after deletion.
     * Displays the deleted task in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert args.length == 1 : "Delete Command should only store 1 argument";
        Task task = tasks.deleteTask(Integer.parseInt(args[0]));
        storage.writeToFile(tasks);
        return ui.showDeletedTask(task, tasks.size());
    }
}
