package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Abstract command class to ensure execute is implemented by other command class.
 */
public abstract class Command {
    public abstract String execute(Ui ui, Storage storage);
}
