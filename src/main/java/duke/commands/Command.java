package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that the Duke program can execute.
 *
 * @author ruiquan
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructs a Command.
     * @param isExit whether the command is an exit command
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns true if the command is an exit command, false otherwise.
     * @return true if the command is an exit command, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command which either leads to a state change,
     * an output or exit of the program.
     * @param tasks the collection of tasks
     * @param ui the user interface that handles input and output
     * @param storage the storage manager that deals with loading from and
     *               saving into a file
     * @throws DukeException if there is an error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
