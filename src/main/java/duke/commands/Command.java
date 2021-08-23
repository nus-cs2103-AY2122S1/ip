package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, Ui ui);

    public abstract boolean isExit();
}
