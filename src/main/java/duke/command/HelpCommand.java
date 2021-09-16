package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * HelpCommand prints help list.
 *
 * @author Chng Zi Hao
 */
public class HelpCommand extends Command {

    /**
     * Prints help list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.formatPrint(ui.getHelp());
        return ui.getHelp();
    }
}
