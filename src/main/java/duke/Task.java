package duke;

/**
 * Represents a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class Constructor that creates a task based on the description.
     *
     * @param description description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a X or nothing depending on whether the task is done.
     *
     * @return an indicator on the doneness of the class.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean filterByKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a String to be written in the storage file.
     *
     * @return String form of the task to be written in the storage file.
     */
    public String toHistory() {
        return " | " + getStatusIcon() + " | " + this.description;
    }
}
