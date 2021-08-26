package duke.commands;

import duke.Ui;
import duke.storage.Storage;

/**
 * Class to handle the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Call duke.Ui to print goodbye.
     * Returns true to break main loop.
     */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        ui.greet(false);
        return true;
    }
}
