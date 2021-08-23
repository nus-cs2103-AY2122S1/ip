package bern.model;

/**
 * A class to represent a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to get whether the task is done or not and represent it with an icon.
     *
     * @return The status icon (X or empty).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * A method to mark this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
