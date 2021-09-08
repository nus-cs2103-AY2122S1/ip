package bobbybot.commands;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

/**
 * Base class for commands
 */
public abstract class Command {

    protected String response;
    /**
     * Executes command
     * @param tasks task list
     * @param ui ui
     * @param storage storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public String getResponse() {
        assert response != null : "Response cannot be null";
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
