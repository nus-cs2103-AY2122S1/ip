package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DukeException;

/**
 * Abstract class that represents a command. It is abstract because the execute method will depend
 * on the type of command.
 */
abstract public class Command {
    /** Whether the command will cause the program to exit */
    private final boolean isExit;

    /**
     * Constructor of Command
     *
     * @param isExit Whether the command will cause the program to stop
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Execute the logic of the command
     *
     * @param tasks List of existing tasks
     * @param ui User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     * @throws DukeException if there is a Duke related exception
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     *
     * @return true if the command will exit after executing.
     *         false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
