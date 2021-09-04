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
     * Deletes the {@code Task} from {@code TaskList} when this function is
     * executed.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            int index = Integer.parseInt(this.taskId) - 1;
            Task deletedTask = taskList.deleteItem(index);
            storage.deleteTaskFromData(index);

            return getReplyMessage(taskList, deletedTask);
        } catch (DukeStorageDeleteException err) {
            throw new DukeStorageDeleteException(err.toString());
        }
    }

    private String getReplyMessage(TaskList taskList, Task deletedTask) {
        return Ui.printMessage("Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                + taskList.getTaskList().size() + " tasks in the list.");
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code false} as this command is not ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
