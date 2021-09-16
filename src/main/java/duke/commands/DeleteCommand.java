package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidInputException;

/**
 * Deletes a task from the task list
 */
public class DeleteCommand extends Command {
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
    public String execute(TaskList task, Storage storage) throws InvalidInputException {
        if (deletionIndex <= 0 || deletionIndex > task.getNumTasks() || task.isEmptyTaskList()) {
            String errorMessage = String.format("Error: Task number '%d' is not within the list", deletionIndex);
            throw new InvalidInputException(errorMessage);
        } else {
            String deletedTaskInfo = task.deleteTask(deletionIndex);
            return String.format("Noted. I've deleted this task: \n   %s\n"
                    + "Now you have %d tasks in the list.", deletedTaskInfo, task.getNumTasks());
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
