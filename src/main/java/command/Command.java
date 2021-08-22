package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
