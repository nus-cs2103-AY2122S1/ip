package duke.commands;

import duke.exceptions.storage.DukeStorageDeleteException;
import duke.storage.Storage;
import duke.storage.TaskList;

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
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.deleteItem(this.taskId);
            storage.deleteTaskFromData(this.taskId);
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
