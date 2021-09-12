package duck.command;

import duck.TaskList;
import duck.task.Task;

/**
 * Represents the command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task newTask;

    /**
     * Constructor of an AddCommand.
     *
     * @param newTask The task to be added.
     */
    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    /**
     * Executes the command of adding the task to the given task list.
     *
     * @param taskList The task list to which the task is added.
     * @return String representing the task being added to the list.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.addTask(newTask);
    }
}
