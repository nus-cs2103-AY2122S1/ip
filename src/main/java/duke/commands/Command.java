package duke.commands;

import duke.Ui;
import duke.storage.Storage;

public abstract class Command {
    public abstract boolean execute(Ui ui, Storage storage);
}
