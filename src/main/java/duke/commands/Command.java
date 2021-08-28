package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException;

    public abstract boolean isExit();
}
