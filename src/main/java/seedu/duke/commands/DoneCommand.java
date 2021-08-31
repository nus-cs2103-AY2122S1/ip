package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageUpdateException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

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
     * Helps to update the task in the list of tasks as done when this function is
     * executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        try {
            taskList.doneItem(index);
            storage.updateDone(index);

            // return Ui.printMessage(
            // Ui.DONE_MESSAGE + "\n" + Ui.INDENT + " " +
            // taskList.getTaskList().get(index).toString());
            return Ui.printMessage(Ui.DONE_MESSAGE + "\n" + taskList.getTaskList().get(index).toString());
        } catch (DukeStorageUpdateException err) {
            throw new DukeStorageUpdateException(err.toString());
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
    }
}
