package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

/**
 * Represents a delete command which
 * removes a task from the database based on
 * the inputted index.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted. */
    private final int index;

    /**
     * Constructor for a DeleteCommand.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task as requested.
     */
    @Override
    public String run() {
        Task task = TaskList.delete(this.index);
        return Message.COMMAND_DELETE.getMessage() + "\t   " + task + "\n";
    }
}
