package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
