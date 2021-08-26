package command;

import duke.TaskList;

/**
 * Represents the command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of deleting the task from the given task list.
     *
     * @param taskList The task list from which the task is deleted.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.deleteTask(index);
    }
}
