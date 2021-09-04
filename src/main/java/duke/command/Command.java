package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

public abstract class Command {
    boolean isExit;

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public boolean isExitCommand() {
        return isExit;
    }
}
