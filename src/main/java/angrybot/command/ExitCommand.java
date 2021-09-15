package angrybot.command;

import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates an Exit commands that deals with exiting the program.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class ExitCommand extends AngryBotCommand {

    /**
     * Constructor for an Exit Command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public ExitCommand(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the exit command. Prints farewellMessage
     * and save the task list onto the local directory.
     *
     * @return Farewell message to be shown on the GUI.
     */
    @Override
    public String execute() {
        storage.save(list.getList());
        return Ui.showFarewellMessage();
    }
}
