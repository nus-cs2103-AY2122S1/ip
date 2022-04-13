package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.InvalidTaskSelectedException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class encapsulates the done command.
 */
public class DoneCommand extends Command {
    private final int taskId;

    /**
     * Constructor of the DoneCommand class.
     *
     * @param taskId The id of the task to be marked as done.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the done command by marking the task as done in the in-memory list,
     * and updating the corresponding task on the txt file.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     * @throws InvalidTaskSelectedException Thrown exception when user inputs invalid task Id.
     * @throws IOException Thrown IO exception when trying to delete tasks on storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskSelectedException, IOException {
        Task task = tasks.getTaskById(this.taskId);
        task.markAsDone();
        storage.update(this.taskId - 1, task); // persist to file
        ui.printTaskMarkedAsDone(task);
    }
}
