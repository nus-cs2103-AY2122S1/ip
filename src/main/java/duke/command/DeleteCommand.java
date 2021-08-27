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
     * @param args {@code String} array with length 1. {@code args[0]} contains the
     *             task number to be deleted.
     */
    public DeleteCommand(String[] args) {
        super(args);
    }

    /**
     * {@inheritDoc} Deletes a task with the given task number in the args. Writes
     * to storage after deletion.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(Integer.parseInt(args[0]));
        storage.writeToFile(tasks);
        ui.showDeletedTask(task, tasks.size());
    }

    /**
     * Returns {@code false}. Program should not terminate after {@code DeleteCommand}.
     * 
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
