package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/** An abstract class for different commands. */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return A boolean value.
     */
    public boolean isExit() {
        return false;
    }
}
