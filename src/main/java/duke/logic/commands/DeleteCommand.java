package duke.logic.commands;

import duke.logic.tasks.TaskList;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Strings for command result messages */
    private static final String EMPTY_TASK_LIST_MSG = "You don't have any tasks!";
    private static final String TASK_NUMBER_OUT_OF_BOUNDS_MSG = "Invalid task number! Must be between 1 and ";
    private static final String TASK_DELETED_MSG = "Noted. I've deleted this task:\n  ";

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
        TaskList taskList = getTaskList();
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = (taskList.size() == 0)
                    ? EMPTY_TASK_LIST_MSG
                    : TASK_NUMBER_OUT_OF_BOUNDS_MSG + taskList.size();
            return new CommandResult(msg);
        }
        return new CommandResult(TASK_DELETED_MSG + taskList.delete(taskNo));
    }
}
