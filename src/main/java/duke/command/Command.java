package duke.command;

import duke.*;
import duke.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
