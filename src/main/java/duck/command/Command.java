package duke.command;

import duke.TaskList;

/**
 * Represents a duke.command to be executed on the task list.
 */
public abstract class Command {

    public Command() {
    }

    /**
     * Executes the duke.command.
     *
     * @param taskList The TaskList used in the duke.command.
     * @return String representing the task being executed.
     */
    public abstract String execute(TaskList taskList);
}
