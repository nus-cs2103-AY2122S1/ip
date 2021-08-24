package command;

import data.Storage;
import data.TaskList;
import data.Ui;

/**
 * An abstract command that contains an execute and isExit method.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
