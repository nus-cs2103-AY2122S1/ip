package duke.command;

import duke.*;
import duke.task.Task;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {return false;}

    public abstract String message(TaskList tasks);
}
