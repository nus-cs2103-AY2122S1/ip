package ligma.command;

import ligma.Storage;
import ligma.TaskList;

/**
 * This interface represents a command specific to the Ligma program.
 */
public interface Command {
    void execute(TaskList tasks, Storage storage);

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    boolean isExit();
}
