package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    public abstract boolean isExit();
}
