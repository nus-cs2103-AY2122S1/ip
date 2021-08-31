package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageDeleteException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;

public class DeleteCommand extends Command {
    private final String taskId;

    /**
     * Primary Constructor.
     * 
     * @param taskId is the id of the task that will be deleted.
     */
    public DeleteCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Helps to delete the task from the list of tasks when this function is
     * executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        try {
            Task deletedTask = taskList.deleteItem(index);
            storage.deleteTaskFromData(index);
            // return Ui.printMessage("Noted. I've removed this task:\n" + Ui.INDENT + " "
            // + deletedTask.toString() + "\n" + Ui.INDENT + "Now you have "
            // + taskList.getTaskList().size() + " tasks in the list.");
            return Ui.printMessage("Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                    + taskList.getTaskList().size() + " tasks in the list.");
        } catch (DukeStorageDeleteException err) {
            throw new DukeStorageDeleteException(err.toString());
        }
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    @Override
    public boolean getIsExit() {
        return false;
    };
}
