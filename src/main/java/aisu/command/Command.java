package aisu.command;

import aisu.AisuException;
import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

/**
 * A Command class.
 *
 * @author Liaw Xin Yan
 */
public abstract class Command {
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
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    public abstract boolean isExit();
}
