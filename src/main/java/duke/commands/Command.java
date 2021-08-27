package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public abstract class Command {
    public boolean isExit = false;
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
