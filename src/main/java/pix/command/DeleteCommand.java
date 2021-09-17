package pix.command;

import pix.Pix;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for the delete command.
     *
     * @param taskNumber Task number to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Triggers the done command which completes a task in the task list.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) throws PixException {
        pix.setLastCommand(this);
        return pix.getTaskList().deleteTask(taskNumber);
    }
}
