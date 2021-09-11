package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.UiInterface;

/**
 * Abstract class to encapsulate a generic Command.
 */
public abstract class Command {

    /**
     * Executes the necessary based on the command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
    public abstract void execute(TaskList taskList, UiInterface ui, Storage storage) throws DukeException;

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    public abstract boolean isExit();

}

