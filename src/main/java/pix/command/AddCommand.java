package pix.command;

import pix.Pix;
import pix.storage.Storage;
import pix.task.Task;
import pix.ui.Ui;


/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the add command.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Triggers the add command which adds a task to the task list.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the message to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) {
        pix.setLastCommand(this);
        return pix.getTaskList().addTask(this.task);
    }
}
