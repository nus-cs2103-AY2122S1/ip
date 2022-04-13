package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task that has a given description and is either done or not done.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Initialises a new instance of task.
     *
     * @param taskDescription The description of the task.
     * @param isDone Marks the task as done or not done.
     */
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Marks this particular task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a boolean representing if the given String is a substring of the task's description.
     *
     * @param string The given String.
     * @return True if the given String is a substring, and false otherwise.
     */
    public boolean hasSubString(String string) {
        return this.taskDescription.contains(string);
    }

    /**
     * Returns a new instance of the task with updated values.
     *
     * @param taskDescription The description of the updated task.
     * @param dateAndTime The date and time of the updated task.
     * @return A task with the updated description or time, or both.
     */
    public abstract Task update(String taskDescription, String dateAndTime) throws DukeException;

    /**
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
     */
    public abstract String parseToString();
}
