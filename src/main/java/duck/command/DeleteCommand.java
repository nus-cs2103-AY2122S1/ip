package duck.command;

import duck.TaskList;

/**
 * Represents the command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for a DeleteCommand.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of deleting the task from the given task list.
     *
     * @param taskList The task list from which the task is deleted.
     * @return String representing the task being deleted.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.deleteTask(index);
    }
}
