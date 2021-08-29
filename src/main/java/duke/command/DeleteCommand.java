package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     * @throws TaskIndexOutOfBoundsException If the task list is accessed with an illegal index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskIndexOutOfBoundsException {
        // Removes a Task from the TaskList instance
        Task removedTask = tasks.deleteTask(taskId);

        // Displays a message indicating the task has been successfully deleted from the list
        String message = "Got it! I've removed this task:\n" + "  " + removedTask + "\n";
        if (tasks.getNumberOfTasks() <= 1) {
            message += "Now you have " + tasks.getNumberOfTasks() + " task in the list.";
        } else {
            message += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        }

        // Saves the current task list to the hard drive
        storage.save(tasks);

        return message;
    }
}
