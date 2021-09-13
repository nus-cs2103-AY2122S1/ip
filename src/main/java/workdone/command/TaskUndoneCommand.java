package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;
import workdone.exception.InvalidTaskNoException;
import workdone.ui.Parser;

/**
 * Represents a command that marks a task as undone. A subclass of the Command class.
 */
public class TaskUndoneCommand extends Command {
    /** Index of the task to be deleted in the task list */
    private final int taskIndex;

    /**
     * Constructor of the class `TaskUndoneCommand`.
     *
     * @param taskIndex Index of a task.
     */
    public TaskUndoneCommand(int taskIndex) {
        super("undone");
        this.taskIndex = taskIndex;
        this.message = "Got it! I've marked this task as undone:\n";
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
     * Executes the command. Marks a task as undone, stores changes and updates the message to be printed.
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

        // Mark task as undone
        assert this.task != null : "task shouldn't be null";
        this.task.setUndone();
        storage.rewriteFile();

        // Update message
        this.message += String.format("  %s\n", this.task.toString());
    }
}
