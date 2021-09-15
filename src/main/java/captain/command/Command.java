package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.TaskList;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    };
}
