package angrybot.command;

import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates a help command that deals with showing a help message.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class HelpCommand extends AngryBotCommand {
    /**
     * Constructor for a HelpCommand.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public HelpCommand(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the command. Shows a long message that contains
     * information on how to use the commands.
     *
     * @return A help message to be displayed on the GUI.
     */
    @Override
    public String execute() {
        return ui.showHelpMessage();
    }
}
