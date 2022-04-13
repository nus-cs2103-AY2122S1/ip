package mango.task;

import mango.MangoException;

/**
 * Represents a task. A <code>Task</code> object has a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructor of a Task, initialised with a completion status and a tag.
     *
     * @param description The description of a task.
     * @param tag The tag relevant to the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, String tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }
    
    /**
     * Retrieves the tag of the task.
     * @return The tag attached to the task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, else false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the completion status of the task.
     *
     * @return The string representation of the completion status of the task.
     */
    public String getStatus() {
        return (isDone ? "true" : "false");
    }

    /**
     * Returns the string representation of the task for saving.
     *
     * @return The string representation of the task.
     */
    public String getSaveFormatString() {
        return String.format("%s:%s:%s::%s\n", this.getType(), this.getStatus(), this.description, this.tag);
    }

    /**
     * Returns the string icon of the completion status of the task.
     *
     * @return The string icon of the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the user's command minimally includes a description of the task during creation.
     *
     * @param userCommand The user's commands in a string array.
     * @return True if the string array is valid, else false.
     * @throws MangoException If the input array has a length of 1.
     */
    public static boolean isValid(String[] userCommand) throws MangoException {
        if (userCommand.length == 1) {
            throw new MangoException(" OOPS!!! The description of a event cannot be empty.");
        }

        return true;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
      this.isDone = true;
    }
}
