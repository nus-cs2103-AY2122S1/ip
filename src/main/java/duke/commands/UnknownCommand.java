package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Deals with invalid inputs.
 */
public class UnknownCommand extends Command{

    public UnknownCommand() {

    }

    /**
     * Calls the Ui to show an error message upon receiving invalid inputs.
     */
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
