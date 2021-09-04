package pix.command;

import pix.Pix;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * Command to exit Pix.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for the Exit Command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Triggers the Exit Command, printing the exit message to the Ui.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the message to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) {
        return "Please don't come back...";
    }
}
