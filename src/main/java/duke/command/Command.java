package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    /**
     * Executes the given command.
     *
     * @param tasks tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     * @return The executed command string.
     * @throws DukeException The exception related to Duke.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public abstract boolean isExit();
}
