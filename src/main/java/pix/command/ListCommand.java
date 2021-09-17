package pix.command;

import pix.Pix;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * Command to list all tasks from the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor for the list command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Triggers the list command which adds a task to the task list.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) {
        return pix.getTaskList().displayList();
    }
}
