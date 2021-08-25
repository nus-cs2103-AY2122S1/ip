package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
