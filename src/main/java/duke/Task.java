package duke;

import java.io.Serializable;

/**
 * Encapsulates the details of a Task object.
 *
 * @author Adam Ho
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description.
     * @param description The task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return A String representation of the task status.
     */
    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Represents task object as a string.
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.description;
    }
}
