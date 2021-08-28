package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
