package duke.command;

import duke.data.TaskList;

/**
 * Class that represents an executable command.
 *
 * @author Wang Hong Yong
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Constructor for Command.
     *
     * @param tasklist Task handler that handles the operation.
     */
    public Command(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * Executes the Command.
     * @return string of output of executing this command.
     */
    public abstract String execute();
}
