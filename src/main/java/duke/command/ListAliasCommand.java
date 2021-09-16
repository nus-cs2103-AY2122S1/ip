package duke.command;

import duke.result.AliasHandler;
import duke.result.Result;
import duke.result.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'listalias'.
 */
public class ListAliasCommand extends Command {

    /**
     * Displays all aliases that the user has configured.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) {
        AliasHandler aliasHandler = Parser.getAliasHandler();
        String message = ui.showMessage("Here are your configured aliases:")
                + ui.showMessage(aliasHandler.toString());
        return new Result(taskList, message);
    }
}
