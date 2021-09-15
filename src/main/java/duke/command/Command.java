package duke.command;

import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command by the user.
 */
public abstract class Command {
    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        FIND,
        EDIT,
        EXIT
    }

    public Command() {}

    /**
     * Executes the command made by the user and returns a string representing the execution result.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @throws DukeException the error received due to DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
