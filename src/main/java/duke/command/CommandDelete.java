package duke.command;

import duke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class CommandDelete extends DukeCommand {
    private int taskId;

    /**
     * Creates a new CommandDelete.
     *
     * @param taskId ID of the task to be deleted.
     */
    public CommandDelete(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.deleteTask(taskId);
    }

    /**
     * As described in DukeCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
