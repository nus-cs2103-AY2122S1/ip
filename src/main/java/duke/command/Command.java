package duke.command;

import duke.*;
import duke.tasks.*;

/** Class for executing commands */
public abstract class Command {
    /**
     * Executes an action with the given parameters.
     *
     * @param tasks A TaskList to work on.
     * @param ui A UI to use for output.
     * @param fc A FileController to use.
     */
    public abstract void execute(TaskList tasks, UI ui, FileController fc);

    /**
     * Returns if this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
