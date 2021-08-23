package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskId;

    /**
     * Constructor for a DeleteCommand instance that takes in a task id.
     *
     * @param taskId The position of the task in the task list.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes a task from the task list and updates the hard disk of the change.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     * @throws TaskIndexOutOfBoundsException If the task list is accessed with an illegal index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException {
        // Removes a Task from the TaskList instance
        Task removedTask = tasks.deleteTask(taskId);

        // Displays a message indicating the task has been successfully deleted from the list
        String message = "Got it! I've removed this task:\n" + "  " + removedTask + "\n";
        if (tasks.getNumberOfTasks() <= 1) {
            message += "Now you have " + tasks.getNumberOfTasks() + " task in the list.";
        } else {
            message += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        }
        ui.showCommandDone(message);

        // Saves the current task list to the hard drive
        storage.save(tasks);
    }

    /**
     * Indicates that this command does not intend to exit the system.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
