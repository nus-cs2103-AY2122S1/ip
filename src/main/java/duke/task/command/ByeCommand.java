package duke.task.command;

import duke.DukeConstants;
import duke.Ui;

/**
 * Class to implement the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     */
    @Override
    public String execute() {
        DukeConstants.isUndoable = false;
        return Ui.printGoodBye();
    }
}
