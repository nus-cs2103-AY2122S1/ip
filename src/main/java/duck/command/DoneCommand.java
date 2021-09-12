package duck.command;

import duck.TaskList;

/**
 * Represents the command that sets a task on the task list to done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructor for a DoneCommand.
     *
     * @param index Index of the task to be set to done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command of setting the given task to done.
     *
     * @param taskList The task list containing the task to be set to done.
     * @return String representing the setting of the task to done.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.setTaskDone(index);
    }
}
