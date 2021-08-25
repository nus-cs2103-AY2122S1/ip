package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
