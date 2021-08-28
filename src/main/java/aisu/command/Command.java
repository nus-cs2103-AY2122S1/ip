package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * A Command class.
 *
 * @author Liaw Xin Yan
 */
public abstract class Command {
    protected String uiText = "";

    /**
     * Does the command.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage Storage used in Aisu.
     * @param ui User interface used in Aisu.
     * @throws AisuException If command fails to be executed.
     */
    public abstract void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException;

    /**
     * Returns the text Aisu responds with for that command. Used with GUI.
     * @return Aisu's response.
     */
    public String showUiText() {
        return this.uiText;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    public abstract boolean isExit();
}
