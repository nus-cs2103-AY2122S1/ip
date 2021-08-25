package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

/**
 * Deletes a task from the task list
 */
public class DeleteCommand extends Command{
    private final int deletionIndex;

    public DeleteCommand(int index) {
        this.deletionIndex = index;
    }

    public int getDeletionIndex() {
        return deletionIndex;
    }

    /**
     * Delete the specified task from the tasklist and reflects the result via the Ui.
     * @throws InvalidInputException if the task does not exist in the list.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws InvalidInputException {
        if (deletionIndex <= 0 || deletionIndex > task.getNumTasks() || task.isEmptyTaskList()) {
            String errorMessage = String.format("   INDEX ERROR: Task number %d does not exist to be deleted", deletionIndex);
            throw new InvalidInputException(errorMessage);
        } else {
            String deletedTaskInfo = task.deleteTask(deletionIndex);
            ui.showDeletedTask(deletedTaskInfo, task.getNumTasks());
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
