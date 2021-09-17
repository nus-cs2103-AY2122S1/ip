package pix.command;

import pix.Pix;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * An empty command that handles the case where the user tries to undo changes that do not exist.
 */
public class EmptyCommand extends Command {
    /**
     * Constructor for the empty command.
     */
    public EmptyCommand() {
        super(false);
    }

    /**
     * Triggers the empty command which displays a message to the user that
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the message to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) throws PixException {
        return ui.showNoLastCommandMessage();
    }
}
