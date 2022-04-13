package jared.task;

/**
 * Represents a task.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts to string for printing list.
     * @return String of task details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts to string for saving in data.
     * @return String of task details in save format.
     */
    public String saveFormat() {
        return String.format("%d _ %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param task the task to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Task task) {
        return 0;
    }
}
