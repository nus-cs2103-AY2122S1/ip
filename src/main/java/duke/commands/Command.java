package duke.commands;

import duke.Ui;
import duke.storage.Storage;

/**
 * Abstract command class to ensure execute is implemented by other command class.
 */
public abstract class Command {
    public abstract boolean execute(Ui ui, Storage storage);
}
