package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * The abstract class that models a Command which can be called to execute.
 */
public abstract class Command {
    boolean isExit;

    /**
     * Constructor.
     * By default is intialized as not to exit the main loop.
     */
    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean indicating if the main loop should be broke.
     * @return A boolean indicating if the main loop should be broke
     */
    public boolean exeResult() {
        return isExit;
    }
}
