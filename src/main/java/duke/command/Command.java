package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class Command {

    /** Checks if is ByeCommand */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

    }
}
