package duke.commands;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted in the task list, 1-based not zero-based. */
    private final int taskNo;

    /**
     * Creates a command to delete a task.
     *
     * @param taskNo The index of the task to be deleted (1-based).
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes the task if the index specified is not out of bounds.
     * No deletion occurs if the index is non-positive or greater than
     * the task list size.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = taskList.size() == 0
                    ? "You don't have any tasks!"
                    : "Invalid take number! Must be between 1 and " + taskList.size();
            return new CommandResult(msg);
        }
        return new CommandResult("Noted. I've deleted this task:\n  " + taskList.delete(taskNo));
    }
}
