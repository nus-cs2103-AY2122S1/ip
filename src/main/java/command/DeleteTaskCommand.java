package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.TaskDoesNotExistException;

/**
 * Command to delete a task.
 *
 * @author Quan Teng Foong
 */
public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String taskIndex) {
        super(taskIndex);
    }

    /**
     * Removes a Task from the taskList.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     * @return String acknowledging that the task has been deleted
     * @throws TaskDoesNotExistException if the task to be deleted does not exist
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TaskDoesNotExistException {
        taskList.delete(Integer.parseInt(super.getExtraInput()) - 1);
        String reply = "Task removed. You now have " + taskList.size() + " tasks remaining. ";
        if (taskList.size() > 0) {
            reply += "\n" + taskList;
        }
        return reply;
    }
}
