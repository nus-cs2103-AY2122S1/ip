package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Abstract class for all types of tasks that Duke can handle.
 */
public abstract class Command {
    public abstract void execute(Storage storage, Ui ui);

    public abstract boolean isExit();
}
