package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

/**
 * Command to mark a task as complete.
 *
 * @author Quan Teng Foong
 */
public class TaskCompletedCommand extends Command {

    public TaskCompletedCommand(String taskIndex) {
        super(taskIndex);
    }

    /**
     * Marks a task as complete.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.doTask(Integer.parseInt(super.getExtraInput()) - 1, taskList);
        if (taskList.toString().equals("")) {
            return "Your todo list is empty!";
        } else {
            return "Checking your todo list...\n" + taskList;
        }
    }
}
