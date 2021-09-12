package duck.command;

import duck.TaskList;

/**
 * Represents a command to be executed on the task list.
 */
public abstract class Command {

    public Command() {
    }

    /**
     * Executes the command.
     *
     * @param taskList The TaskList used in the command.
     * @return String representing the task being executed.
     */
    public abstract String execute(TaskList taskList);
}
