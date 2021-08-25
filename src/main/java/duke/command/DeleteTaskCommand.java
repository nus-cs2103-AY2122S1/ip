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
     * printed. Returns true because the program is still running.
     *
     * @param taskList A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     * @return Whether the program is still running.
     * @throws InvalidTaskNoException If task number is invalid.
     */
    @Override
    public boolean execute(TaskList taskList, Storage storage) throws InvalidTaskNoException {
        try {
            this.task = taskList.get(this.taskIndex);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNoException();
        }
        taskList.removeFromList(this.task);
        storage.removeFromFile(taskList.indexOf(this.task));
        this.message = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %o tasks in the list.\n",
                this.task, taskList.getNumOfTasks());
        return true;
    }
}
