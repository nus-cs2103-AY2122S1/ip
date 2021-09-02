package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ExitCommand handles the command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Handles the display of the goodbye message.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
