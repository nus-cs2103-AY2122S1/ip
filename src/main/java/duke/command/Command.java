package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Abstract class that represents a command. It is abstract because the execute method will depend
 * on the type of command.
 */
public abstract class Command {
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
     * Execute the logic of the command and returns a CommandResult.
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     * @throws DukeException if there is a Duke related exception
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     *
     * @return true if the command will exit after executing.
     *         false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
