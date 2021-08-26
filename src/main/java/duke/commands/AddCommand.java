package duke.commands;

import duke.exceptions.RepeatedTaskException;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand task is a Command that adds a task to a list of tasks.
 */
public class AddCommand extends Command {
    Task task;

    /**
     * Constructor - Initialises the task to be added.
     *
     * @param task input task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Takes in a list of tasks and the ui. Checks whether the task is already present in the list of tasks
     * and if it is, it throws a RepeatedTaskException. Else it adds the task to the list of tasks.
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.containsTask(task)) {
            throw new RepeatedTaskException();
        } else {
            taskList.add(task);
            ui.displayAdd(task, taskList);
        }
    }
}
