package command;

import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of setting the given task to done.
     *
     * @param ui Ui not used in this execution.
     * @param taskList The task list containing the task to be set to done.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.setTaskDone(index);
    }
}
