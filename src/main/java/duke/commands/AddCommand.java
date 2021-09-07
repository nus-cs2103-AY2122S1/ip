package duke.commands;

import duke.exceptions.RepeatedTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The AddCommand task is a Command that adds a task to a list of tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Initialises the task to be added.
     *
     * @param task input task to be added to the list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the specified task to the task list if it is not already inside, throws a RepeatedTaskException otherwise.
     *
     * @param taskList Current list of tasks.
     * @param ui Instance of the UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        if (taskList.containsTask(task)) {
            throw new RepeatedTaskException();
        } else {
            taskList.add(task);
            return ui.displayAdd(task, taskList);
        }
    }
}