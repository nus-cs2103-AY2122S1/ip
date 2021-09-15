package angrybot.command;

import angrybot.exception.AngryBotException;
import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates commands that can be executed by the AngryBot program.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public abstract class AngryBotCommand {
    protected final Ui ui;
    protected final Storage storage;
    protected final TaskList list;

    /**
     * Constructor for a AngryBotCommand.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public AngryBotCommand(Ui ui, Storage storage, TaskList list) {
        this.ui = ui;
        this.storage = storage;
        this.list = list;
    }

    /**
     * Executes the command.
     *
     * @return A message to be displayed on the GUI.
     * @throws AngryBotException When an error occurred.
     */
    public abstract String execute() throws AngryBotException;

}
