package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** An abstract class for different commands. */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return A boolean value.
     */
    public boolean isExit() {
        return false;
    }
}
