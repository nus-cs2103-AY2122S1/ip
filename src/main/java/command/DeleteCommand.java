package command;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of deleting the task from the given task list.
     *
     * @param ui Ui not used in this execution.
     * @param taskList The task list from which the task is deleted.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.deleteTask(index);
    }
}
