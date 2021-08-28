package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates the delete command.
 *
 * @author Teo Sin Yee
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Instantiates a DeleteCommand
     *
     * @param taskId Index of task to be deleted
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the task from the task list.
     * Updates data in storage file.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param storage Storage for Duke.
     * @param ui User interface.
     * @throws DukeException If there is error deleting the task or updating data.
     */
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.deleteTask(taskId);
        taskHandler.updateData();
    }
}