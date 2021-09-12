package duke.task.command;

import duke.DukeConstants;
import duke.Ui;

/**
 * The command to display the help menu.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command.
     *
     * @return The output message of the command.
     */
    @Override
    public String execute() {
        DukeConstants.isUndoable = false;
        return Ui.getHelpMenu();
    }
}
