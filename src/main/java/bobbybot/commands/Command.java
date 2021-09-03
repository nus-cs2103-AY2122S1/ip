package bobbybot.commands;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

/**
 * Base class for commands
 */
public abstract class Command {
    //@author Tenebrius1 --reus
    /**
     * Executes command and get response
     * @param tasks task list
     * @param ui ui
     * @param storage storage
     * @return
     */
    public abstract String getResponse(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
