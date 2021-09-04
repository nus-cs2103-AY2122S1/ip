package pix.command;

import pix.Pix;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * Command to find certain tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the find command.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Triggers the find command which finds all tasks which contains the keyword in the task list.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the list of tasks that contain the keyword.
     */

    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) throws PixException {
        return pix.getTaskList().findTasks(keyword);
    }
}
