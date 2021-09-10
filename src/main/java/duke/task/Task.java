package duke.task;

import duke.DukeException;

/**
 * A wrapper for a task used by DukeList.
 *
 * @author Wong Yun Rui Chris
 */
public abstract class Task {
    protected TaskName taskName;
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to initialise a duke.task.Task.
     *
     * @param description The String description/name of the task
     * @param isDone The Boolean of if the task is done
     */
    protected Task(TaskName taskname, String description, Boolean isDone) {
        this.taskName = taskname;
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Gets the corresponding status icon for this task.
     *
     * @return The String representation of the status for this task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the status of this task as done.
     *
     * @return The new String representation of this duke.task.Task after the status is marked as done
     */
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Returns the task formatted as a text string to be stored as data in a text file.
     * The task is stored with 1 and 0 representing the isDone status of the task with 1
     * representing the task is done while 0 is not done separated by a vertical line
     * then followed by the description of the task.
     *
     * @return The String representation of this task
     */
    public String toData() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Return if the provided string matches with that of the task's description.
     *
     * @param keyword The String used to check against the task's description if they match
     * @return The boolean if the keyword matches with the task's description
     */
    public boolean matchKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Factory method to return the corresponding task with the given task name.
     *
     * @param taskName Name of the task to be returned
     * @param description Description for the task
     * @param secondInput Time description for event and deadline task and should be null for todo task
     * @param isDone The state of whether the task is done
     * @return The task from the given inputs provided
     * @throws DukeException Exception specific to duke.Duke
     */
    public static Task ofTask(TaskName taskName, String description, String secondInput, Boolean isDone)
            throws DukeException {
        switch (taskName) {
        case TODO:
            return new ToDo(description, isDone);

        case DEADLINE:
            return new Deadline(description, secondInput, isDone);

        case EVENT:
            return new Event(description, secondInput, isDone);
        default:
            throw new DukeException("Task name provided is incorrect");
        }
    }

    public abstract Task copy();
}
