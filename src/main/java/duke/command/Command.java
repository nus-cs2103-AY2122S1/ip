package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

public abstract class Command {
    public abstract void execute(TaskList task, UI userInt, Storage storage) throws DukeException;

    public abstract boolean isExit();

    public abstract Task getTask();
}
