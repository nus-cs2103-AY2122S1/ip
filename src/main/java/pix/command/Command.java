package pix.command;

import pix.Pix;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.ui.Ui;

/**
 * Abstract class command for all other commands to inherit from.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Constructor of a command.
     *
     * @param isExit Shows if command is the exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Shows is command is the exit command.
     *
     * @return Returns whether the command is the exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Triggers the command and gives a string output depending on the command triggered.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns a string output depending on the command.
     * @throws PixException Throws multiple exceptions depending on the tasks.
     */
    public abstract String trigger(Pix pix, Storage storage, Ui ui) throws PixException;
}
