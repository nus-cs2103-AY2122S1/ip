package ponyo.data.task;

/**
 * A task class that stores the description and status of a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object with its input description.
     *
     * @param description Describes the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the done status of a task.
     *
     * @return the string representation of whether a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the done status of a task.
     *
     * @return the integer (bool) representation of whether a task is done
     */
    public int getStatusNumber() {
        return (isDone ? 1 : 0); // mark done task with 1
    }

    /**
     * Mark a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task into a string to be added to the file.
     *
     * @return A string representation of a Task object to add in file.
     */
    public String toStringInFile() {
        return String.format("%d - %s", this.getStatusNumber(), this.description);
    }

    /**
     * Converts the Task into a string.
     *
     * @return A string representation of a Task object.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }
}
