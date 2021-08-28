package pix.command;

import pix.exception.PixException;
import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

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
     * @param storage Storage class to store information.
     * @param taskList Task list class to store information about tasks.
     * @param ui Ui class to display messages to the user.
     *
     * @return Returns a string output depending on the command.
     *
     * @throws PixException Throws multiple exceptions depending on the tasks.
     */
    public abstract String trigger(Storage storage, TaskList taskList, Ui ui) throws PixException;
}
