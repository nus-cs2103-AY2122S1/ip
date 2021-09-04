package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageUpdateException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;

public class DoneCommand extends Command {
    private final String taskId;

    /**
     * Primary Constructor.
     * 
     * @param taskId is the task that will be updated as done in the list of tasks.
     */
    public DoneCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Updates the {@code Task} in the {@code TaskList} as done when this function
     * is executed.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        try {
            Task updatedTask = taskList.doneItem(index);
            storage.updateDone(index);

            return getReplyMessage(taskList, updatedTask);

        } catch (DukeStorageUpdateException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    private String getReplyMessage(TaskList taskList, Task updatedTask) {
        return Ui.printMessage(Ui.DONE_MESSAGE + "\n" + updatedTask.toString());
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
    }
}
