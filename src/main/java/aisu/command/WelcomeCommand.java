package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to display the welcome message.
 *
 * @author Liaw Xin Yan
 */
public class WelcomeCommand extends Command {
    /** {@inheritDoc} */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        this.execute();
    }

    /**
     * An overloaded method to execute the welcome message without needing any inputs.
     */
    public void execute() {
        this.uiText = Ui.getWelcomeMessage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
