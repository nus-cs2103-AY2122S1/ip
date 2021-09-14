package duke.commands;

import duke.Main;
import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Class to handle the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Exit the application by calling Main.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        Main.exit();
        return ui.greet(false);
    }
}
