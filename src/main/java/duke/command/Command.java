package duke.command;


import duke.FileController;
import duke.tasks.TaskList;

/** Class for executing commands */
public abstract class Command {
    /**
     * Executes an action with the given parameters.
     *
     * @param tasks A TaskList to work on.
     * @param fc A FileController to use.
     */
    public abstract String execute(TaskList tasks, FileController fc);
}
