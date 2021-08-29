package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Commands inputted by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * If the command is the exit command.
     * @return True if it is the exit command, false otherwise.
     */
    public abstract boolean isExit();
}
