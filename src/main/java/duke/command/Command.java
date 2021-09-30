package duke.command;

import duke.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Returns the result of the execution of the command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the command.
     */
    public abstract String execute(TaskList tasks);
}