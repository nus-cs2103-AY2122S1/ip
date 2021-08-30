package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.InvalidTaskNoException;

/**
 * Represents a command that deletes a task from the task list. A subclass of the Command class.
 */
public class DeleteTaskCommand extends Command {
    /** Index of the task to be deleted in the task list */
    int taskIndex;

    /**
     * Constructor of the class `DeleteTaskCommand`.
     *
     * @param taskIndex Index of a task.
     */
    public DeleteTaskCommand(int taskIndex) {
        super("delete");
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command. Deletes a task from the list, stores changes and updates the message to be
     * printed.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     * @throws InvalidTaskNoException If task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws InvalidTaskNoException {
        // Get task from index
        try {
            this.task = tasks.get(this.taskIndex);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNoException();
        }

        // Remove the task
        tasks.removeFromList(this.task);
        storage.removeFromFile(tasks.indexOf(this.task));

        // Update message
        this.message = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n",
                this.task, tasks.getNumOfTasks());
    }
}
