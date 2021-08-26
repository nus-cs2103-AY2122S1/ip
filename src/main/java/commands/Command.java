package commands;

import task.TaskList;
import ui.Ui;
import duke.DukeException;
import storage.Storage;

public abstract class Command {
    public boolean isExit = false;
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
