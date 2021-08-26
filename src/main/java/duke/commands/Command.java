package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a command to be executed on a task list.
 */
public abstract class Command {

    /** The task list to be operated on. */
    TaskList taskList;

    /**
     * Sets the task list that this command operates on.
     *
     * @param tl The task list to be operated on.
     */
    public void setTaskList(TaskList tl) {
        taskList = tl;
    }

    /**
     * Executes this command and returns the result.
     *
     * @return The result of executing this command.
     */
    public abstract CommandResult execute();

}
