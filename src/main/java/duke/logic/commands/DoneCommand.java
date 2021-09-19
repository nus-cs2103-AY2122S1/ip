package duke.logic.commands;

import duke.logic.tasks.TaskList;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    /** Strings for command result messages */
    private static final String EMPTY_TASK_LIST_MSG = "You don't have any tasks!";
    private static final String TASK_NUMBER_OUT_OF_BOUNDS_MSG = "Invalid task number! Must be between 1 and ";
    private static final String TASK_MARKED_AS_DONE_MSG = "I've marked this task as done:\n  ";
    private static final String TASK_ALREADY_DONE_MSG = "Task already done.";

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
        TaskList taskList = getTaskList();
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = (taskList.size() == 0)
                    ? EMPTY_TASK_LIST_MSG
                    : TASK_NUMBER_OUT_OF_BOUNDS_MSG + taskList.size();
            return new CommandResult(msg);
        }
        return (taskList.markAsDone(taskNo))
                ? new CommandResult(TASK_MARKED_AS_DONE_MSG + taskList.get(taskNo))
                : new CommandResult(TASK_ALREADY_DONE_MSG);
    }
}
