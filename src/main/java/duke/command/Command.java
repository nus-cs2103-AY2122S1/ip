package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.data.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents a command by the user.
 */
public abstract class Command {
    private CommandTypes commandType;
    public enum CommandTypes {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        UNDO,
        FIND,
        BYE
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
