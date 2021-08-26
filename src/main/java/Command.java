package duke.command;

import java.io.IOException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Command is the abstract base class for all Commands.
 */

public abstract class Command {

    /** Executes the Command using the arguments
     *
     * @param tasks Tasklist to execute the command on.
     * @param ui UI to reflect the changes.
     * @param storage Storage to store the TaskList after a command has been executed.
     * @throws IOException On Input error.
     * @see IOException
     */

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Indicates whether a Command is an ExitCommand or not.
     *
     * @return true if Command is an ExitCommand, false if Command is not an ExitCommand
     */

    public boolean isExit() {
        return false;
    }
    
}