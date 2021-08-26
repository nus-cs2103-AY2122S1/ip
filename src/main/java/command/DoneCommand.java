package command;

import duke.TaskList;

/**
 * Represents the command that sets a task on the task list to done.
 */
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of setting the given task to done.
     *
     * @param taskList The task list containing the task to be set to done.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.setTaskDone(index);
    }
}
