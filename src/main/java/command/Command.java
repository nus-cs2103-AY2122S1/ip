package command;

import exception.PixException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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

    public abstract String trigger(Storage storage, TaskList taskList, Ui ui) throws PixException;
}