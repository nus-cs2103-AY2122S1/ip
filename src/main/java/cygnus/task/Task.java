package cygnus.task;

/**
 * Represents a Task which encapsulates a description
 * and a boolean flag indicating if the Task is complete.
 *
 * @author Joshua Yong
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description The given Task description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Outputs this task as a String.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        // Mark done task with X
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + description;
    }

    /**
     * Sets the isDone boolean flag of the Task to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the done status of the Task.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the description of the Task.
     *
     * @return Description of the Task.
     */
    public String getDescription() {
        return description;
    }
}
