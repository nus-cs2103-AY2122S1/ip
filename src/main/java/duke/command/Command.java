package duke.command;

import duke.DukeException;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command, modify given taskManager and output to screen using ui.
     *
     * @param taskManager Instance of TaskManager that handles tasks.
     * @param ui          Instance of Ui that handles output to screen.
     * @throws DukeException If something goes wrong with the Duke command.
     */
    public abstract void execute(TaskManager taskManager, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
