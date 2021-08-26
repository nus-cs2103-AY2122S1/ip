package duke.commands;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    /** The index of the task to mark as done, 1-based not zero-based. */
    private final int taskNo;

    /**
     * Creates a command to mark a task as done.
     *
     * @param taskNo The index of the task to mark as done (1-based).
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Marks the task as done if the index is not out of bounds and
     * the task is not done already. No changes occur if the index is
     * non-positive or greater than the task list size, or if the
     * task is already done.
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
        return (taskList.markAsDone(taskNo))
                ? new CommandResult("I've marked this task as done:\n  " + taskList.get(taskNo))
                : new CommandResult("Task already done.");
    }
}
