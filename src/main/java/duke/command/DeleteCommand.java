package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

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
     * @throws IOException If the data cannot be saved in the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskIndexOutOfBoundsException,
            IOException {
        Task removedTask = tasks.deleteTask(taskId);
        assert removedTask != null : "The deleted task should be returned";

        // Constructs a message indicating the task has been successfully deleted from the list
        String message = "Got it! I've removed this task:\n" + "  " + removedTask + "\nNow you have ";
        int numberOfTasks = tasks.getNumberOfTasks();
        assert numberOfTasks >= 0 : "Number of tasks should not be negative";
        if (numberOfTasks <= 1) {
            message += numberOfTasks + " task in the list.";
        } else {
            message += numberOfTasks + " tasks in the list.";
        }

        // Saves the updated task list to the hard drive
        storage.save(tasks);

        return message;
    }
}
