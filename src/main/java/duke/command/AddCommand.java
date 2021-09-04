package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * AddCommand adds a task to the task list.
 *
 * @author Chng Zi Hao
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to add to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert task != null : "Task is empty";
        String message = taskList.addTask(task);
        ui.formatPrint(message);
        return message;
    }
}
