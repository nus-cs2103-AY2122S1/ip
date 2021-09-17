package pix.command;

import pix.Pix;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.ui.Ui;

public class UndoCommand extends Command {
    /**
     * Constructor for the undo command.
     */
    public UndoCommand() {
        super(false);
    }

    /**
     * Triggers the undo command which undoes the last command inputted.
     *
     * @param pix Pix class that get initialized with Pix.
     * @param storage Storage class to store the data in.
     * @param ui Ui class to display the messages to the user.
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Pix pix, Storage storage, Ui ui) throws PixException {
        Command lastCommand = pix.getLastCommand();
        if (lastCommand instanceof EmptyCommand) {
            return ui.showNoLastCommandMessage();
        }

        pix.setLastCommand(this);
        pix.undoLastTask();
        storage.undidChange(pix.getTaskList());
        return ui.showUndoMessage();
    }
}
