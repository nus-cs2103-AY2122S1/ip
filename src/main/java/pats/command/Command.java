package pats.command;

import pats.PatsException;
import pats.Storage;
import pats.TaskList;
import pats.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList t, Ui ui, Storage storage) throws PatsException;

    /**
     * Is this the exit command?
     * @return if this is exit command
     */
    public abstract boolean isExit();
}
