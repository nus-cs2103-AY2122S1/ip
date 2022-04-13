package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;
import workdone.exception.InvalidTaskNoException;
import workdone.ui.Parser;

/**
 * Represents a command that marks a task as done. A subclass of the Command class.
 */
public class TaskDoneCommand extends Command {
    /** Index of the task to be deleted in the task list */
    private final int taskIndex;

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
     * Returns the index of the task.
     *
     * @return Index of the task.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Executes the command. Marks a task as done, stores changes and updates the message to be printed.
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
            Parser.popInvalidCommand();
            throw new InvalidTaskNoException();
        }

        // Mark task as done
        assert this.task != null : "task shouldn't be null";
        this.task.setDone();
        storage.rewriteFile();

        // Update message
        this.message += String.format("  %s\n", this.task.toString());
    }
}
