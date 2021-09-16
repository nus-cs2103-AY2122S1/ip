package aisu.command;

import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to display the tasklist in readable format.
 *
 * @author Liaw Xin Yan
 */
public class ShowListCommand extends Command {

    /**
     * Displays the tasklist.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        if (tasklist.isEmpty()) {
            this.uiText = Ui.formatText("Oops, the list is empty! :O");
        } else {
            this.uiText = Ui.formatText("Here's what you have in your list:", tasklist.toString());
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
