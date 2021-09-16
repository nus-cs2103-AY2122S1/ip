package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    /**
     * Is this the exit command?
     * @return if this is exit command
     */
    public abstract boolean isExit();
}
