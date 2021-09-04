package aisu.command;

import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to exit the Aisu program.
 *
 * @author Liaw Xin Yan
 */
public class ExitCommand extends Command {

    /**
     * Sets the UI to display a goodbye message.
     * @param tasklist TaskList used in Aisu.
     * @param storage Storage used in Aisu.
     * @param ui User interface used in Aisu.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        this.uiText = Ui.getGoodbyeMessage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return true;
    }
}
