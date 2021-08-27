package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public abstract class Command {
    /**
     * Indicates if the command is an exit command.
     */
    public boolean isExit = false;

    /**
     * Executes the user's command.
     *
     * @param tasks Current tasklist.
     * @param ui User interface of the Duke programme
     * @param storage Storage object which updates the local file.
     * @throws DukeException
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
