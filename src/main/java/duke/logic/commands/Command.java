package duke.logic.commands;

import duke.logic.tasks.TaskList;

/**
 * Represents a command to be executed on a task list.
 */
public abstract class Command {

    /** The task list to be operated on. */
    private TaskList taskList;

    TaskList getTaskList() {
        return taskList;
    }

    /**
     * Sets the task list that this command operates on.
     *
     * @param tl The task list to be operated on.
     */
    public void setTaskList(TaskList tl) {
        assert tl != null;
        taskList = tl;
    }

    /**
     * Executes this command and returns the result.
     *
     * @return The result of executing this command.
     */
    public abstract CommandResult execute();

}
