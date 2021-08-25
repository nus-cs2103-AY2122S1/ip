package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

abstract public class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit;
}
