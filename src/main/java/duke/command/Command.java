package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Interface used to execute commands.
 */
public interface Command {

    /**
     * Returns a String after executing appropriate commands.
     *
     * @param taskList TaskList to manage current user's tasks.
     * @param ui Ui to print messages to the user.
     * @param storage Storage to save and load tasks from disk.
     * @return String Duke's response to user.
     * @throws DukeException If there are input or parsing errors.
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
