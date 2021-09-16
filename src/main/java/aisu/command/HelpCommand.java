package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to display the help page.
 *
 * @author Liaw Xin Yan
 */
public class HelpCommand extends Command {
    /** {@inheritDoc} */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        this.execute();
    }

    /**
     * An overloaded method to execute the help message without needing any inputs.
     */
    public void execute() {
        this.uiText = Ui.getHelpMessage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
