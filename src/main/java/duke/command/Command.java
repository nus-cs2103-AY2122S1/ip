package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
    public abstract boolean isExit();
}

