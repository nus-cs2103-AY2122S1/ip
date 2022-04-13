package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deals with the Command given by user and implement the corresponding actions.
 */
public abstract class Command {
    /**
     * Indicates the actions taken for different command.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui The Ui of Duke.
     * @param storage The Storage of Duke.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if the current command is an exit command.
     *
     * @return If current command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
