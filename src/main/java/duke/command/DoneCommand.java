package duke.command;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * This DoneCommand class represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskId;

    /**
     * Constructor for a DoneCommand instance that takes in a task id.
     *
     * @param taskId The position of the task in the task list.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Marks a task as done and updates the hard disk of the change.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     * @throws TaskIndexOutOfBoundsException If the task list is accessed with an illegal index.
     * @throws TaskAlreadyDoneException If the task has already been marked as done.
     * @throws IOException If the data cannot be saved in the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskIndexOutOfBoundsException,
            TaskAlreadyDoneException, IOException {
        Task completedTask = tasks.markAsDone(this.taskId);
        assert completedTask != null : "The task should be marked as done and returned accordingly";

        // Saves the updated task list to the hard drive
        storage.save(tasks);

        // Returns a message indicating the task has been successfully marked as done
        return "Nice! I've marked this task as done:\n  " + completedTask.toString();
    }
}
