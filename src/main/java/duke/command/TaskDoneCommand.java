package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.InvalidTaskNoException;

/**
 * Represents a command that marks a task as done. A subclass of the Command class.
 */
public class TaskDoneCommand extends Command {
    /** Index of the task to be deleted in the task list */
    int taskIndex;
    /**
     * Constructor of the class `TaskDoneCommand`.
     *
     * @param taskIndex Index of a task.
     */
    public TaskDoneCommand(int taskIndex) {
        super("done");
        this.taskIndex = taskIndex;
        this.message = "Nice! I've marked this task as done:\n";
    }

    /**
     * Executes the command. Marks a task as done, stores changes and updates the message to be printed.
     * Returns true because the program is still running.
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
        this.task.markAsDone();
        storage.rewriteFile();
        this.message += String.format("  %s\n", this.task.toString());
        return true;
    }
}
