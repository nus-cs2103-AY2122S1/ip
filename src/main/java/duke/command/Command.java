package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Commands inputted by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * If the command is the exit command.
     * @return True if it is the exit command, false otherwise.
     */
    public abstract boolean isExit();
}
