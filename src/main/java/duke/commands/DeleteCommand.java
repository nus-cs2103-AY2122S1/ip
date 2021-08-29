package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.InvalidTaskSelectedException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class encapsulates the delete command.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructor of the DeleteCommand class.
     *
     * @param taskId The id of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the delete command by deleting the task in the in-memory list,
     * and deleting the corresponding task on the txt file.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     * @throws InvalidTaskSelectedException Thrown exception when user inputs invalid task Id.
     * @throws IOException Thrown IO exception when trying to delete tasks on storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidTaskSelectedException, IOException {
        Task task = tasks.deleteTaskById(this.taskId);
        storage.delete(taskId);
        ui.printTaskDeleted(task);
        ui.printNumOfTasks(tasks);
    }
}
