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
        this.uiText = ui.formatText("Here's what you have in your list:", tasklist.toString());
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
