package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;

/**
 * Represents a command with the type and payload of the command.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, storage and ui.
     *
     * @param taskList The task list to execute the command with.
     * @param storage  The storage to execute the command with.
     * @param uiPane       The UI to execute the command with.
     */
    public abstract void execute(TaskList taskList, Storage storage, UiPane uiPane) throws DukeException;
}
