package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean exeResult() {
        return isExit;
    }
}
