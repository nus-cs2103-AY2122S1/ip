package aisu.command;

import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

/**
 * Command to exit the Aisu program.
 *
 * @author Liaw Xin Yan
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        this.uiText = ui.getGoodbyeMessage();
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
