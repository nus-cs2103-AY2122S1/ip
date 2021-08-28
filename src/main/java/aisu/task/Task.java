package aisu.task;

import aisu.AisuException;

/**
 * The Task class.
 * A Task contains a description and a status of being done or not.
 * There are three types of tasks: Todo, Deadline, Event.
 *
 * @author Liaw Xin Yan
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws AisuException {
        if (description.length() == 0) {
            throw new AisuException("The description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the icon that represents whether the task is done or not.
     *
     * @return The icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]");
    }

    /**
     * Marks task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data.
     */
    public abstract String parseData();

    /**
     * Returns a string representation of the object.
     *
     * @return The Task in readable format.
     */
    public abstract String toString();
}
