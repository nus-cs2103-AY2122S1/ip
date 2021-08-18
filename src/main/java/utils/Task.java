package utils;

/**
 * The Task class encapsulates a task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor to create a new components.Task object.
     * By default created tasks are not done.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     * @return "X" for done or " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
