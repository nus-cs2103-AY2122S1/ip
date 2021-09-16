package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

/**
 * Marks a task from the task list as complete
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Marks the specified task from the tasklist as complete and reflects the result via the Ui.
     * @throws InvalidInputException if the task does not exist in the list.
     */
    public String execute(TaskList task, Storage storage) throws DukeException {
        if (taskIndex <= 0 || taskIndex > task.getNumTasks() || task.isEmptyTaskList()) {
            String errorMessage = String.format("Error: Task number '%d' is not within the list", taskIndex);
            throw new InvalidInputException(errorMessage);
        } else {
            String completedTaskInfo = task.completeTask(taskIndex);
            return String.format("Nice! I've marked this task as done: \n   %s", completedTaskInfo);
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
