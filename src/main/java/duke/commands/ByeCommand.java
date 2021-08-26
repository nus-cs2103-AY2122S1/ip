package duke.commands;

import duke.Ui;
import duke.storage.Storage;

<<<<<<< HEAD
public class ByeCommand extends Command {

    /**
     * Call duke.Ui to print goodbye
     * Returns true to break main loop
     */

=======
/**
 * Class to handle the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Call duke.Ui to print goodbye.
     * Returns true to break main loop.
     */
>>>>>>> branch-A-JavaDoc
    @Override
    public boolean execute(Ui ui, Storage storage) {
        ui.greet(false);
        return true;
    }
}
