package duke.util.commands;

import duke.util.commons.Messages;
import duke.util.tasks.Task;
import duke.util.tasks.TaskList;
import duke.util.ui.Ui;
/**
 * Command representing the addition of a
 * task to the task list.
 *
 */
public class AddCommand implements Command {
    private final TaskList tasks;
    private final Task task;

    //should use the same Ui -- or can use a different one?
    private final Ui ui;

    /**
     * The constructor of the AddCommand object.
     *
     * @param tasks The list of tasks to add to.
     * @param task The task to be add.
     */
    public AddCommand(TaskList tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
        this.ui = new Ui();
    }

    @Override
    public String execute() {
        if (TaskList.isAdded(task, tasks)) {
            return String.format(Messages.TASK_ALREADY_ADDED, task.toString());
        } else {
            tasks.add(task);
            return ui.getOutputFromTaskAdded(task, tasks.size());
        }
    }
}
